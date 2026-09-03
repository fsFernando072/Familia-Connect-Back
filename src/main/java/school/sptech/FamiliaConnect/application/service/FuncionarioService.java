package school.sptech.FamiliaConnect.application.service;

import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.FamiliaConnect.application.ports.in.FuncionarioUseCase;
import school.sptech.FamiliaConnect.infraestructure.config.GerenciadorTokenJwt;
import school.sptech.FamiliaConnect.application.ports.in.ArquivoUseCase;
import school.sptech.FamiliaConnect.infraestructure.web.dto.funcionario.FuncionarioTokenDto;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.infraestructure.web.mapper.ArquivoMapper;
import school.sptech.FamiliaConnect.infraestructure.web.mapper.FuncionarioMapper;
import school.sptech.FamiliaConnect.domain.entity.Arquivo;
import school.sptech.FamiliaConnect.domain.entity.CategoriaArquivo;
import school.sptech.FamiliaConnect.domain.entity.Cargo;
import school.sptech.FamiliaConnect.domain.entity.Funcionario;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.CargoRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.FuncionarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService implements FuncionarioUseCase {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final ArquivoUseCase arquivoUseCase;
    private final CategoriaArquivoService categoriaArquivoService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public FuncionarioService(FuncionarioRepository funcionarioRepository,
                              CargoRepository cargoRepository,
                              GerenciadorTokenJwt gerenciadorTokenJwt,
                              AuthenticationManager authenticationManager,
                              PasswordEncoder passwordEncoder,
                              ArquivoUseCase arquivoUseCase,
                              CategoriaArquivoService categoriaArquivoService) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.arquivoUseCase = arquivoUseCase;
        this.categoriaArquivoService = categoriaArquivoService;
    }

    // Funções ---------------------------------------------------------------------------------------------------------

    public List<Funcionario> listar(){

        return funcionarioRepository.findAll();

    }

    public Funcionario listarPorId(Integer id){

        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);

        if(funcionario.isEmpty()){
            throw new EntidadeNaoEncontradaException("Funcionário não encontrado pelo id");
        }

        return funcionario.get();

    }

    public Funcionario salvar(Funcionario funcionario, MultipartFile foto){

        Cargo cargo = cargoRepository.findById(funcionario.getCargo().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cargo não encontrado pelo id"));


        funcionario.setCargo(cargo);
        funcionario.setFoto(resolverFotoFuncionario(foto, null));

        String senhaCriptografada = passwordEncoder.encode(funcionario.getSenha());
        funcionario.setSenha(senhaCriptografada);

        return funcionarioRepository.save(funcionario);

    }

    public Funcionario atualizar(Integer id, Funcionario funcionario, MultipartFile foto){

        Cargo cargo = cargoRepository.findById(funcionario.getCargo().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cargo não encontrado pelo id"));

        Funcionario funcionarioExistente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado pelo id"));

        funcionario.setId(id);
        funcionario.setCargo(cargo);
        funcionario.setFoto(resolverFotoFuncionario(foto, funcionarioExistente.getFoto()));
        String senhaCriptografada = passwordEncoder.encode(funcionario.getSenha());
        funcionario.setSenha(senhaCriptografada);

        return funcionarioRepository.save(funcionario);

    }

    // Envia a foto recebida para a "parte de arquivos" (ArquivoService), já na categoria FUNCIONARIOS,
    // e retorna a referência ao Arquivo salvo (antes retornava uma URL em String).
    // Se nenhuma foto nova for enviada, mantém a referência atual (fotoAtual).
    private Arquivo resolverFotoFuncionario(MultipartFile foto, Arquivo fotoAtual) {

        if (foto == null || foto.isEmpty()) {
            return fotoAtual;
        }

        if (fotoAtual != null) {
            arquivoUseCase.deletarPorId(fotoAtual.getId());
        }

        CategoriaArquivo categoria = categoriaArquivoService.buscarPorNome("funcionarios");
        Arquivo arquivo = ArquivoMapper.toEntity(foto, categoria);

        return arquivoUseCase.salvar(arquivo);
    }

    @Transactional
    public void deletar(Integer id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("O cargo com o id não foi encontrado");
        }

        Funcionario funcionario = listarPorId(id);

        funcionarioRepository.deleteById(id);
        arquivoUseCase.deletarPorId(funcionario.getFoto().getId());
    }

    public FuncionarioTokenDto autenticar(Funcionario usuario) {

        //envelopando credenciais do usuário para futura autenticação.
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuario.getCpf(), usuario.getSenha());

        //Beleza, usuário existe la no banco, e agr está autenticado, com payload e roles / authorities (era pra ter)
        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        //pega todos os dados do usuário autenticado (incluindo o ID) para gerar o token
        Funcionario usuarioAutenticado =
                funcionarioRepository.findByCpf(usuario.getCpf())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "CPF do usuário não cadastrado", null)
                        );

        //fala pro contexto do spring/jwt que é esse o usuário autenticado no momento da req (para validações no fluxo mais pra frente)
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //gera token.
        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return FuncionarioMapper.of(usuarioAutenticado, token);
    }
}

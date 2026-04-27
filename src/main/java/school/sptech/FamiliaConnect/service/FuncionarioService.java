package school.sptech.FamiliaConnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.FamiliaConnect.config.GerenciadorTokenJwt;
import school.sptech.FamiliaConnect.dto.funcionario.FuncionarioRequestDto;
import school.sptech.FamiliaConnect.dto.funcionario.FuncionarioTokenDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.FuncionarioMapper;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.model.Funcionario;
import school.sptech.FamiliaConnect.repository.CargoRepository;
import school.sptech.FamiliaConnect.repository.FuncionarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Construtores ----------------------------------------------------------------------------------------------------

    public FuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
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

    public Funcionario salvar(FuncionarioRequestDto requestDto){

        Cargo cargo = cargoRepository.findById(requestDto.getCargoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cargo não encontrado pelo id"));

        Funcionario funcionario = FuncionarioMapper.toModel(requestDto);
        funcionario.setCargo(cargo);

        String senhaCriptografada = passwordEncoder.encode(funcionario.getSenha());
        funcionario.setSenha(senhaCriptografada);

        return funcionarioRepository.save(funcionario);

    }

    public Funcionario atualizar(FuncionarioRequestDto requestDto, Integer id){

        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cargo não encontrado pelo id"));

        if(!funcionarioRepository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Funcionário não encontrado pelo id");
        }

        Funcionario funcionario = FuncionarioMapper.toModel(requestDto);
        funcionario.setId(id);
        funcionario.setCargo(cargo);

        return funcionarioRepository.save(funcionario);

    }

    public FuncionarioTokenDto autenticar(Funcionario usuario) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuario.getCargo(), usuario.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Funcionario usuarioAutenticado =
                funcionarioRepository.findByCpf(usuario.getCpf())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return FuncionarioMapper.of(usuarioAutenticado, token);
    }
}

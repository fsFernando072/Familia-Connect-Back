package school.sptech.FamiliaConnect.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.dto.familia.FamiliaListResponseDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaRequestDto;
import school.sptech.FamiliaConnect.dto.pessoa.PessoaRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.ArquivoMapper;
import school.sptech.FamiliaConnect.mapper.EnderecoMapper;
import school.sptech.FamiliaConnect.mapper.PessoaMapper;
import school.sptech.FamiliaConnect.model.Arquivo;
import school.sptech.FamiliaConnect.model.CategoriaArquivo;
import school.sptech.FamiliaConnect.model.Endereco;
import school.sptech.FamiliaConnect.model.Entrega;
import school.sptech.FamiliaConnect.model.Familia;
import school.sptech.FamiliaConnect.model.Pessoa;
import school.sptech.FamiliaConnect.repository.EnderecoRepository;
import school.sptech.FamiliaConnect.repository.EntregaRepository;
import school.sptech.FamiliaConnect.repository.FamiliaRepository;
import school.sptech.FamiliaConnect.repository.PessoaRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FamiliaService {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final FamiliaRepository familiaRepository;
    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;
    private final EntregaRepository entregaRepository;
    private final EnderecoService enderecoService;
    private final PessoaService pessoaService;
    private final ArquivoService arquivoService;
    private final CategoriaArquivoService categoriaArquivoService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public FamiliaService(FamiliaRepository familiaRepository, EnderecoRepository enderecoRepository,
                          PessoaRepository pessoaRepository, EntregaRepository entregaRepository,
                          EnderecoService enderecoService, PessoaService pessoaService,
                          ArquivoService arquivoService, CategoriaArquivoService categoriaArquivoService) {
        this.familiaRepository = familiaRepository;
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
        this.entregaRepository = entregaRepository;
        this.enderecoService = enderecoService;
        this.pessoaService = pessoaService;
        this.arquivoService = arquivoService;
        this.categoriaArquivoService = categoriaArquivoService;
    }

    // Funções ---------------------------------------------------------------------------------------------------------

    @Transactional
    public Familia salvar(FamiliaRequestDto dto, MultipartFile foto) {

        Endereco endereco = EnderecoMapper.toModel(dto.getEndereco());
        endereco = enderecoService.salvar(endereco);

        Familia familia = new Familia();
        familia.setDataCadastro(dto.getDataCadastro());
        familia.setFoto(resolverFotoFamilia(foto, null));
        familia.setPossuiPrioridade(dto.getPossuiPrioridade());
        familia.setEndereco(endereco);
        familia = familiaRepository.save(familia);

        Pessoa responsavel = PessoaMapper.toModel(dto.getResponsavel());
        responsavel.setFamilia(familia);
        responsavel.setResponsavel(true);
        pessoaService.salvar(responsavel);

        salvarDependentes(dto.getDependentes(), familia);

        return familia;
    }

    public Page<FamiliaListResponseDto> listar(Pageable pageable){

        return familiaRepository.findAllCustomized(pageable);

    }

    public Familia listarPorId(Integer id){

        return familiaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("A família com o id não foi encontrada"));
    }

    public List<Pessoa> listarIntegrantes(Integer id){

        return familiaRepository.findByIdFamilia(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("A família com o id não foi encontrada"));
    }

    @Transactional
    public Familia atualizar(Integer idFamilia, FamiliaRequestDto dto, MultipartFile foto) {

        Familia familiaExistente = familiaRepository.findById(idFamilia)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Família não encontrada pelo id"));

        Endereco enderecoAtualizado = enderecoService.atualizar(
                familiaExistente.getEndereco().getId(), EnderecoMapper.toModel(dto.getEndereco()));

        familiaExistente.setFoto(resolverFotoFamilia(foto, familiaExistente.getFoto()));
        familiaExistente.setPossuiPrioridade(dto.getPossuiPrioridade());
        familiaExistente.setEndereco(enderecoAtualizado);
        familiaExistente = familiaRepository.save(familiaExistente);

        List<Pessoa> integrantes = listarIntegrantes(idFamilia);

        Pessoa responsavelExistente = integrantes.stream()
                .filter(Pessoa::getResponsavel)
                .findFirst()
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O responsável da família não foi encontrado"));

        Pessoa responsavelAtualizado = PessoaMapper.toModel(dto.getResponsavel());
        responsavelAtualizado.setResponsavel(true);
        pessoaService.atualizar(responsavelExistente.getId(), responsavelAtualizado);

        List<Pessoa> dependentesExistentes = integrantes.stream()
                .filter(pessoa -> !Boolean.TRUE.equals(pessoa.getResponsavel()))
                .toList();

        atualizarDependentes(dependentesExistentes, dto.getDependentes(), familiaExistente);

        return familiaExistente;
    }

    private void atualizarDependentes(List<Pessoa> dependentesExistentes, List<PessoaRequestDto> dependentesDto, Familia familia) {

        List<PessoaRequestDto> dependentes = dependentesDto != null ? dependentesDto : List.of();

        Map<String, Pessoa> dependentesExistentesPorCpf = dependentesExistentes.stream()
                .filter(pessoa -> pessoa.getCpf() != null && !pessoa.getCpf().isBlank())
                .collect(Collectors.toMap(Pessoa::getCpf, pessoa -> pessoa));

        Set<Integer> idsMantidos = new HashSet<>();

        for (PessoaRequestDto dependenteDto : dependentes) {

            Pessoa dependenteExistente = dependenteDto.getCpf() != null
                    ? dependentesExistentesPorCpf.get(dependenteDto.getCpf())
                    : null;

            if (dependenteExistente != null) {
                Pessoa dependenteAtualizado = PessoaMapper.toModel(dependenteDto);
                dependenteAtualizado.setResponsavel(false);
                pessoaService.atualizar(dependenteExistente.getId(), dependenteAtualizado);
                idsMantidos.add(dependenteExistente.getId());
            } else {
                Pessoa novoDependente = PessoaMapper.toModel(dependenteDto);
                novoDependente.setFamilia(familia);
                novoDependente.setResponsavel(false);
                pessoaService.salvar(novoDependente);
            }
        }

        List<Pessoa> dependentesRemovidos = dependentesExistentes.stream()
                .filter(pessoa -> !idsMantidos.contains(pessoa.getId()))
                .toList();

        List<Entrega> entregasRemovidas = entregaRepository.findByPessoaIn(dependentesRemovidos);
        entregaRepository.deleteAll(entregasRemovidas);
        pessoaRepository.deleteAll(dependentesRemovidos);
    }

    @Transactional
    public void deletar(Integer id) {

        Familia familia = familiaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("A família com o id não foi encontrada"));

        List<Pessoa> integrantes = listarIntegrantes(id);

        List<Entrega> entregas = entregaRepository.findByPessoaIn(integrantes);
        entregaRepository.deleteAll(entregas);

        pessoaRepository.deleteAll(integrantes);
        familiaRepository.delete(familia);
        arquivoService.deletarPorId(familia.getFoto().getId());
        enderecoRepository.delete(familia.getEndereco());
    }

    private Arquivo resolverFotoFamilia(MultipartFile foto, Arquivo fotoAtual) {

        if (foto == null || foto.isEmpty()) {
            return fotoAtual;
        }

        if (fotoAtual != null) {
            arquivoService.deletarPorId(fotoAtual.getId());
        }

        CategoriaArquivo categoria = categoriaArquivoService.buscarPorNome("familias");
        Arquivo arquivo = ArquivoMapper.toEntity(foto, categoria);

        return arquivoService.salvar(arquivo);
    }

    private void salvarDependentes(List<PessoaRequestDto> dependentes, Familia familia) {

        if (dependentes == null) {
            return;
        }

        for (PessoaRequestDto dependente : dependentes) {
            Pessoa dependentePessoa = PessoaMapper.toModel(dependente);
            dependentePessoa.setFamilia(familia);
            dependentePessoa.setResponsavel(false);
            pessoaService.salvar(dependentePessoa);
        }
    }
}

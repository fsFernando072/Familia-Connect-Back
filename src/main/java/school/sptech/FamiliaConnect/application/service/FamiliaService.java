package school.sptech.FamiliaConnect.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.application.ports.in.ArquivoUseCase;
import school.sptech.FamiliaConnect.application.ports.in.FamiliaUseCase;
import school.sptech.FamiliaConnect.domain.entity.*;
import school.sptech.FamiliaConnect.domain.entity.carteirinha.ProcessamentoCarteirinha;
import school.sptech.FamiliaConnect.infraestructure.web.dto.familia.FamiliaListResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.familia.FamiliaRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.pessoa.PessoaRequestDto;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.infraestructure.web.mapper.ArquivoMapper;
import school.sptech.FamiliaConnect.infraestructure.web.mapper.EnderecoMapper;
import school.sptech.FamiliaConnect.infraestructure.web.mapper.PessoaMapper;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.EnderecoRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.EntregaRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.FamiliaRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.PessoaRepository;
import school.sptech.FamiliaConnect.infraestructure.producer.dto.CarteirinhaMensagemDto;
import school.sptech.FamiliaConnect.infraestructure.producer.CarteirinhaProducer;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FamiliaService implements FamiliaUseCase {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final FamiliaRepository familiaRepository;
    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;
    private final EntregaRepository entregaRepository;
    private final EnderecoService enderecoService;
    private final PessoaService pessoaService;
    private final ArquivoUseCase arquivoUseCase;
    private final CategoriaArquivoService categoriaArquivoService;
    private final ValidadorImagem validadorImagem;
    private final CarteirinhaProducer carteirinhaProducer;
    private final RedisService redisService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public FamiliaService(FamiliaRepository familiaRepository, EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository, EntregaRepository entregaRepository, EnderecoService enderecoService, PessoaService pessoaService, ArquivoUseCase arquivoUseCase, CategoriaArquivoService categoriaArquivoService, ValidadorImagem validadorImagem, CarteirinhaProducer carteirinhaProducer, RedisService redisService) {
        this.familiaRepository = familiaRepository;
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
        this.entregaRepository = entregaRepository;
        this.enderecoService = enderecoService;
        this.pessoaService = pessoaService;
        this.arquivoUseCase = arquivoUseCase;
        this.categoriaArquivoService = categoriaArquivoService;
        this.validadorImagem = validadorImagem;
        this.carteirinhaProducer = carteirinhaProducer;
        this.redisService = redisService;
    }


    // Funções ---------------------------------------------------------------------------------------------------------

    @Transactional
    public FamiliaCarteirinha salvar(FamiliaRequestDto dto, MultipartFile foto) {

        Endereco endereco = EnderecoMapper.toModel(dto.getEndereco());
        endereco = enderecoService.salvar(endereco);

        Familia familia = new Familia();
        familia.setFoto(resolverFotoFamilia(foto, null));
        familia.setPossuiPrioridade(dto.getPossuiPrioridade());
        familia.setEndereco(endereco);
        familia = familiaRepository.save(familia);

        Pessoa responsavel = PessoaMapper.toModel(dto.getResponsavel());
        responsavel.setFamilia(familia);
        responsavel.setResponsavel(true);
        pessoaService.salvar(responsavel);

        salvarDependentes(dto.getDependentes(), familia);

        //salvar no redis
        ProcessamentoCarteirinha dadosCarteirinha = redisService.createTicketRedis(familia.getId(), responsavel.getNome());
//
//        carteirinhaProducer.enviar(new CarteirinhaMensagemDto(
//                familia.getId(),
//                responsavel.getNome(),
//                CarteirinhaProducer.formatarCpf(responsavel.getCpf()),
//                responsavel.getTelefone()));

        //ja vai ficar ouvindo aqui a fila de resposta

        return new FamiliaCarteirinha(familia, dadosCarteirinha);
    }

    public Page<FamiliaListResponseDto> listar(String nomeResponsavel, Pageable pageable){

        String termoPesquisa = nomeResponsavel != null ? nomeResponsavel : "";

        return familiaRepository.findAllCustomized(termoPesquisa, pageable);

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

        familiaExistente.setFoto(resolverFotoFamilia(foto, familiaExistente.getFoto()));
        familiaExistente.setPossuiPrioridade(dto.getPossuiPrioridade());
        familiaExistente.setEndereco(enderecoAtualizado);
        familiaExistente = familiaRepository.save(familiaExistente);

        return familiaExistente;
    }

    private void atualizarDependentes(List<Pessoa> dependentesExistentes, List<PessoaRequestDto> dependentesDto, Familia familia) {

        List<PessoaRequestDto> dependentes = dependentesDto != null ? dependentesDto : List.of();

        Map<Integer, Pessoa> existentesPorId = dependentesExistentes.stream()
                .filter(pessoa -> pessoa.getId() != null)
                .collect(Collectors.toMap(Pessoa::getId, pessoa -> pessoa));

        Set<Integer> idsMantidos = new HashSet<>();

        for (PessoaRequestDto dependenteDto : dependentes) {

            Pessoa dependenteExistente = dependenteDto.getId() != null
                    ? existentesPorId.get(dependenteDto.getId())
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
        if (familia.getFoto() != null) {
            arquivoUseCase.deletarPorId(familia.getFoto().getId());
        }
        enderecoRepository.delete(familia.getEndereco());
    }

    public Integer searchByCarteirinha(String idCarteirinha) {
        Familia familia = familiaRepository.findByIdCarteirinha(idCarteirinha)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("A família com a carteirinha informada não foi encontrada"));

        return familia.getId();
    }

    private Arquivo resolverFotoFamilia(MultipartFile foto, Arquivo fotoAtual) {

        if (foto == null || foto.isEmpty()) {
            return fotoAtual;
        }

        validadorImagem.validarAvatar(foto);

        CategoriaArquivo categoria = categoriaArquivoService.buscarPorNome("familias");

        Arquivo novo = arquivoUseCase.salvar(ArquivoMapper.toEntity(foto, categoria));
        if (fotoAtual != null) {
            arquivoUseCase.deletarPorId(fotoAtual.getId());
        }

        return novo;
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

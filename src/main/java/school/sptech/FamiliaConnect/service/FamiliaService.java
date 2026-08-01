package school.sptech.FamiliaConnect.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.FamiliaConnect.dto.familia.FamiliaCompletaRequestDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaListResponseDto;
import school.sptech.FamiliaConnect.dto.pessoa.PessoaRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.EnderecoMapper;
import school.sptech.FamiliaConnect.mapper.PessoaMapper;
import school.sptech.FamiliaConnect.model.Endereco;
import school.sptech.FamiliaConnect.model.Familia;
import school.sptech.FamiliaConnect.model.Pessoa;
import school.sptech.FamiliaConnect.repository.EnderecoRepository;
import school.sptech.FamiliaConnect.repository.FamiliaRepository;

import java.util.List;

@Service
public class FamiliaService {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final FamiliaRepository familiaRepository;
    private final EnderecoRepository enderecoRepository;
    private final EnderecoService enderecoService;
    private final PessoaService pessoaService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public FamiliaService(FamiliaRepository familiaRepository, EnderecoRepository enderecoRepository,
                          EnderecoService enderecoService, PessoaService pessoaService) {
        this.familiaRepository = familiaRepository;
        this.enderecoRepository = enderecoRepository;
        this.enderecoService = enderecoService;
        this.pessoaService = pessoaService;
    }

    // Funções ---------------------------------------------------------------------------------------------------------

    public Familia salvar(Familia familia){

        Endereco endereco = enderecoRepository.findById(familia.getEndereco().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O endereco com o id não foi encontrada"));


        familia.setEndereco(endereco);

        return familiaRepository.save(familia);

    }

    @Transactional
    public Familia salvarCompleta(FamiliaCompletaRequestDto dto) {

        Endereco endereco = EnderecoMapper.toModel(dto.getEndereco());
        endereco = enderecoService.salvar(endereco);

        Familia familia = new Familia();
        familia.setDataCadastro(dto.getDataCadastro());
        familia.setFotoFamilia(dto.getFotoFamilia());
        familia.setPossuiPrioridade(dto.getPossuiPrioridade());
        familia.setEndereco(endereco);
        familia = familiaRepository.save(familia);

        Pessoa responsavel = PessoaMapper.toModel(dto.getResponsavel());
        responsavel.setFamilia(familia);
        pessoaService.salvar(responsavel);

        if (dto.getDependentes() != null) {
            for (PessoaRequestDto dependente : dto.getDependentes()) {
                Pessoa dependentePessoa = PessoaMapper.toModel(dependente);
                dependentePessoa.setFamilia(familia);
                pessoaService.salvar(dependentePessoa);
            }
        }

        return familia;
    }

    public Page<FamiliaListResponseDto> listar(Pageable pageable){

        return familiaRepository.findAllCustomized(pageable);

    }

    public Familia listarPorId(Integer id){

        return familiaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("A família com o id não foi encontrada"));
    }

    public Familia atualizar(Integer idFamilia, Familia familiaAtualizada) {

        Familia familiaExistente = familiaRepository.findById(idFamilia)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Família não encontrada pelo id"));

        Endereco endereco = enderecoRepository.findById(
                        familiaAtualizada.getEndereco().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado pelo id"));

        familiaExistente.setFotoFamilia(familiaAtualizada.getFotoFamilia());

        familiaExistente.setPossuiPrioridade(familiaAtualizada.getPossuiPrioridade());

        familiaExistente.setEndereco(endereco);

        return familiaRepository.save(familiaExistente);
    }

    public List<Pessoa> listarPorIdFamilia(Integer id){

        return familiaRepository.findByIdFamilia(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("A família com o id não foi encontrada"));
    }
}

package school.sptech.FamiliaConnect.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.familia.FamiliaListResponseDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
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

    // Construtores ----------------------------------------------------------------------------------------------------

    public FamiliaService(FamiliaRepository familiaRepository, EnderecoRepository enderecoRepository) {
        this.familiaRepository = familiaRepository;
        this.enderecoRepository = enderecoRepository;
    }

    // Funções ---------------------------------------------------------------------------------------------------------

    public Familia salvar(Familia familia){

        Endereco endereco = enderecoRepository.findById(familia.getEndereco().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O endereco com o id não foi encontrada"));


        familia.setEndereco(endereco);

        return familiaRepository.save(familia);

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

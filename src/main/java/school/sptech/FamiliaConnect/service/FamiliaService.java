package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.familia.FamiliaRequestDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaResponseDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.FamiliaMapper;
import school.sptech.FamiliaConnect.model.Endereco;
import school.sptech.FamiliaConnect.model.Familia;
import school.sptech.FamiliaConnect.repository.EnderecoRepository;
import school.sptech.FamiliaConnect.repository.FamiliaRepository;

import java.util.List;
import java.util.Optional;

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

    public List<Familia> listar(){

        return familiaRepository.findAll();

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
}

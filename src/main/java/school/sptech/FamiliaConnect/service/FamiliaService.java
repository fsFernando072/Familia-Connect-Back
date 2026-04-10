package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
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

    public Familia salvar(Familia familia, Integer idEndereco){

        Optional<Endereco> endereco = enderecoRepository.findById(idEndereco);

        if(endereco.isEmpty()){
            throw new EntidadeNaoEncontradaException("Entidade não encontrada");
        }

        familia.setEndereco(endereco.get());

        Familia familiaCadastrada = familiaRepository.save(familia);

        return familiaCadastrada;

    }

    public List<Familia> listar(){

        List<Familia> familias = familiaRepository.findAll();

        // Arrumar Exception
        if(familias.isEmpty()){
            throw new EntidadeNaoEncontradaException("Entidades não encontradas");
        }

        return familias;

    }

    public Familia listarPorId(Integer id){

        Optional<Familia> familia = familiaRepository.findById(id);

        if(familia.isEmpty()){
            throw new EntidadeNaoEncontradaException("Entidade não encontrada");
        }

        return familia.get();

    }
}

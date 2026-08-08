package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.endereco.EnderecoRequestDto;
import school.sptech.FamiliaConnect.dto.endereco.EnderecoResponseDto;
import school.sptech.FamiliaConnect.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.EnderecoMapper;
import school.sptech.FamiliaConnect.model.Endereco;
import school.sptech.FamiliaConnect.model.Estado;
import school.sptech.FamiliaConnect.repository.EnderecoRepository;
import school.sptech.FamiliaConnect.repository.EstadoRepository;

@Service
public class EnderecoService {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final EnderecoRepository enderecoRepository;
    private final EstadoRepository estadoRepository;

    // Construtores ------------------------------------------------------------------------------------------------------

    public EnderecoService(EnderecoRepository enderecoRepository, EstadoRepository estadoRepository) {
        this.enderecoRepository = enderecoRepository;
        this.estadoRepository = estadoRepository;
    }

    // Funções ---------------------------------------------------------------------------------------------------------

    public Endereco salvar(Endereco endereco){

        if (enderecoRepository.existsByLogradouroAndNumero(endereco.getLogradouro(), endereco.getNumero())){
            throw new EntidadeJaCadastradaException("Entidade Endereço já cadastrada");
        }

        Estado estado = estadoRepository.findById(endereco.getEstado().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O estado com o id não foi encontrado"));


        endereco.setEstado(estado);

        return enderecoRepository.save(endereco);
    }

    public Endereco atualizar(Integer id, Endereco enderecoAtualizado) {

        Endereco enderecoExistente = enderecoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O endereço com o id não foi encontrado"));

        Estado estado = estadoRepository.findById(enderecoAtualizado.getEstado().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O estado com o id não foi encontrado"));

        enderecoExistente.setCep(enderecoAtualizado.getCep());
        enderecoExistente.setBairro(enderecoAtualizado.getBairro());
        enderecoExistente.setLogradouro(enderecoAtualizado.getLogradouro());
        enderecoExistente.setNumero(enderecoAtualizado.getNumero());
        enderecoExistente.setComplemento(enderecoAtualizado.getComplemento());
        enderecoExistente.setCidade(enderecoAtualizado.getCidade());
        enderecoExistente.setEstado(estado);

        return enderecoRepository.save(enderecoExistente);
    }

}

package school.sptech.FamiliaConnect.application.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.application.ports.in.EnderecoUseCase;
import school.sptech.FamiliaConnect.domain.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.domain.entity.Endereco;
import school.sptech.FamiliaConnect.domain.entity.Estado;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.EnderecoRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.EstadoRepository;

@Service
public class EnderecoService implements EnderecoUseCase {

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

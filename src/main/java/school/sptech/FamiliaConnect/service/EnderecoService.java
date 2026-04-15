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

    public Endereco salvar(EnderecoRequestDto enderecoRequestDto){

        if (enderecoRepository.existsByLogradouroAndNumero(enderecoRequestDto.getLogradouro(), enderecoRequestDto.getNumero())){
            throw new EntidadeJaCadastradaException("Entidade Endereço já cadastrada");
        }

        Estado estado = estadoRepository.findById(enderecoRequestDto.getEstadoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O estado com o id não foi encontrado"));

        Endereco enderecoParaSalvar = EnderecoMapper.toModel(enderecoRequestDto);
        enderecoParaSalvar.setEstado(estado);

        return enderecoRepository.save(enderecoParaSalvar);
    }

}

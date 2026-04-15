package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.endereco.EnderecoRequestDto;
import school.sptech.FamiliaConnect.dto.endereco.EnderecoResponseDto;
import school.sptech.FamiliaConnect.model.Endereco;
import school.sptech.FamiliaConnect.model.Estado;

import java.util.List;


public class EnderecoMapper {

    public static Endereco toModel(EnderecoRequestDto enderecoRequestDto){

        Endereco endereco = new Endereco();
        endereco.setBairro(enderecoRequestDto.getBairro());
        endereco.setComplemento(enderecoRequestDto.getComplemento());
        endereco.setCep(enderecoRequestDto.getCep());
        endereco.setLogradouro(enderecoRequestDto.getLogradouro());
        endereco.setNumero(enderecoRequestDto.getNumero());

        return endereco;

    }

    public static EnderecoResponseDto toResponse(Endereco endereco){

        Estado estadoEntidade = endereco.getEstado();

        EnderecoResponseDto.EnderecoEstado estado = new EnderecoResponseDto.EnderecoEstado();

        estado.setId(estadoEntidade.getId());
        estado.setNome(estadoEntidade.getNome());
        estado.setSigla(estadoEntidade.getSigla());

        EnderecoResponseDto dto = new EnderecoResponseDto();
        dto.setId(endereco.getId());
        dto.setCep(endereco.getCep());
        dto.setBairro(endereco.getBairro());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setCidade(endereco.getCidade());
        dto.setEnderecoEstado(estado);

        return dto;

    }

    public static List<EnderecoResponseDto> toResponse(List<Endereco> enderecos) {
        return enderecos.stream()
                .map(EnderecoMapper::toResponse)
                .toList();
    }

}

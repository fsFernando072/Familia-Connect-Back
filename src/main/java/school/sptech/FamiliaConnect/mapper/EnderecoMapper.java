package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.endereco.EnderecoRequestDto;
import school.sptech.FamiliaConnect.dto.endereco.EnderecoResponseDto;
import school.sptech.FamiliaConnect.model.Endereco;
import school.sptech.FamiliaConnect.model.Estado;


public class EnderecoMapper {

    public static Endereco toModel(EnderecoRequestDto enderecoRequestDto, Estado estado){

        Endereco endereco = new Endereco(
                enderecoRequestDto.getBairro(),
                enderecoRequestDto.getComplemento(),
                enderecoRequestDto.getCep(),
                estado,
                enderecoRequestDto.getLogradouro(),
                enderecoRequestDto.getNumero()
        );

        return endereco;

    }

    public static EnderecoResponseDto toResponse(Endereco endereco){

        Estado estado = endereco.getEstado();

        EnderecoResponseDto.EnderecoEstado enderecoEstado = new EnderecoResponseDto.EnderecoEstado();

        enderecoEstado.setId(estado.getId());

        EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto(
            endereco.getId(),
                endereco.getCep(),
                endereco.getBairro(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                enderecoEstado
        );

        return enderecoResponseDto;

    }

}

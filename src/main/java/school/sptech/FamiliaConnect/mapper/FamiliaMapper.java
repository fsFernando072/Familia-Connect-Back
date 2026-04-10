package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.familia.FamiliaRequestDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaResponseDto;
import school.sptech.FamiliaConnect.model.Familia;

import java.util.List;

public class FamiliaMapper {

    public static Familia toModel(FamiliaRequestDto familiaRequestDto){

        Familia familia = new Familia(
            familiaRequestDto.getDataCadastro(),
                familiaRequestDto.getFotoFamilia()
        );

        return familia;

    }

    public static FamiliaResponseDto toResponse(Familia familia){

        FamiliaResponseDto.FamiliaEndereco.EnderecoEstado familiaEnderecoEstadoResponse =
                new FamiliaResponseDto.FamiliaEndereco.EnderecoEstado(familia.getEndereco().getEstado().getNome());

        FamiliaResponseDto.FamiliaEndereco familiaEndereco = new FamiliaResponseDto.FamiliaEndereco(
                familia.getEndereco().getCep(),
                familia.getEndereco().getBairro(),
                familia.getEndereco().getLogradouro(),
                familia.getEndereco().getNumero(),
                familia.getEndereco().getComplemento(),
                familiaEnderecoEstadoResponse
        );

        FamiliaResponseDto familiaResponseDto = new FamiliaResponseDto(
            familia.getId(),
                familia.getDataCadastro(),
                familia.getFotoFamilia(),
                familiaEndereco
        );

        return familiaResponseDto;

    }

    public static List<FamiliaResponseDto> toResponseList(List<Familia> familias){

        return familias.stream()
                .map(FamiliaMapper::toResponse)
                .toList();

    }

}

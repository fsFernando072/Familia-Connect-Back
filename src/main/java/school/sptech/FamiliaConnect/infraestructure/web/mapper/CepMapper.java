package school.sptech.FamiliaConnect.infraestructure.web.mapper;

import school.sptech.FamiliaConnect.domain.entity.EnderecoCep;
import school.sptech.FamiliaConnect.infraestructure.web.dto.cep.EnderecoCepResponseDto;

public class CepMapper {

    public static EnderecoCepResponseDto toResponse(EnderecoCep enderecoCep) {
        EnderecoCepResponseDto dto = new EnderecoCepResponseDto();
        dto.setCep(enderecoCep.getCep());
        dto.setLogradouro(enderecoCep.getLogradouro());
        dto.setComplemento(enderecoCep.getComplemento());
        dto.setBairro(enderecoCep.getBairro());
        dto.setLocalidade(enderecoCep.getLocalidade());
        dto.setUf(enderecoCep.getUf());
        return dto;
    }
}

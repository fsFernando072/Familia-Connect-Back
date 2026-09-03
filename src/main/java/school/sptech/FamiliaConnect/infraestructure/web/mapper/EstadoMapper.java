package school.sptech.FamiliaConnect.infraestructure.web.mapper;

import school.sptech.FamiliaConnect.infraestructure.web.dto.estado.EstadoResponseDto;
import school.sptech.FamiliaConnect.domain.entity.Estado;

import java.util.List;

public class EstadoMapper {

    public static EstadoResponseDto toResponse(Estado estado) {
        EstadoResponseDto dto = new EstadoResponseDto();
        dto.setId(estado.getId());
        dto.setNome(estado.getNome());
        dto.setSigla(estado.getSigla());

        return dto;
    }

    public static List<EstadoResponseDto> toResponse(List<Estado> estados) {
        return estados.stream()
                .map(EstadoMapper::toResponse)
                .toList();
    }

}

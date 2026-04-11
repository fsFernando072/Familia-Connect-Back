package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.Estado.EstadoRequestDto;
import school.sptech.FamiliaConnect.dto.Estado.EstadoResponseDto;
import school.sptech.FamiliaConnect.model.Estado;

import java.util.List;

public class EstadoMapper {

    public static Estado toModel(EstadoRequestDto dto) {
        return new Estado(
                dto.getNome(),
                dto.getSigla()
        );
    }

    public static EstadoResponseDto toResponse(Estado estado) {
        return new EstadoResponseDto(
                estado.getId(),
                estado.getNome(),
                estado.getSigla()
        );
    }

    public static List<EstadoResponseDto> toResponseList(List<Estado> estados) {
        return estados.stream()
                .map(EstadoMapper::toResponse)
                .toList();
    }

}

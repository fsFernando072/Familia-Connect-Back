package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.Estado.EstadoRequestDto;
import school.sptech.FamiliaConnect.dto.Estado.EstadoResponseDto;
import school.sptech.FamiliaConnect.model.Estado;

import java.util.List;

public class EstadoMapper {

    public static Estado toModel(EstadoRequestDto dto) {
        Estado estado = new Estado();
        estado.setNome(dto.getNome());
        estado.setSigla(dto.getSigla());

        return estado;
    }

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

package school.sptech.FamiliaConnect.infraestructure.web.mapper;

import school.sptech.FamiliaConnect.infraestructure.web.dto.grauParentesco.GrauParentescoResponseDto;
import school.sptech.FamiliaConnect.domain.entity.GrauParentesco;

import java.util.List;

public class GrauParentescoMapper {

    public static GrauParentescoResponseDto toResponse(GrauParentesco grauParentesco) {

        return new GrauParentescoResponseDto(grauParentesco.getId(), grauParentesco.getGrau());

    }

    public static List<GrauParentescoResponseDto> toResponse(List<GrauParentesco> grausParentesco) {

        return grausParentesco.stream()
                .map(GrauParentescoMapper::toResponse)
                .toList();

    }

}

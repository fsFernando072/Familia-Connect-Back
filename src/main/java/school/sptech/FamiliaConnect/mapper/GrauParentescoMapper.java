package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.grauParentesco.GrauParentescoResponseDto;
import school.sptech.FamiliaConnect.model.GrauParentesco;

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

package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.deficiencia.DeficienciaRequestDto;
import school.sptech.FamiliaConnect.dto.deficiencia.DeficienciaResponseDto;
import school.sptech.FamiliaConnect.model.Deficiencia;

import java.util.List;

public class DeficienciaMapper {

    public static Deficiencia toModel(DeficienciaRequestDto deficienciaRequestDto){

        Deficiencia deficiencia = new Deficiencia(
                deficienciaRequestDto.getNome()
        );

        return deficiencia;

    }

    public static DeficienciaResponseDto toResponse(Deficiencia deficiencia){

        DeficienciaResponseDto deficienciaResponseDto = new DeficienciaResponseDto(
                deficiencia.getNome(),
                deficiencia.getId()
        );

        return deficienciaResponseDto;

    }

    public static List<DeficienciaResponseDto> toResponseList(List<Deficiencia> deficiencias){

        return deficiencias.stream()
                .map(DeficienciaMapper::toResponse)
                .toList();

    }

}

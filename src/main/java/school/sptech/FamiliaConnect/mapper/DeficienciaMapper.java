package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.deficiencia.DeficienciaRequestDto;
import school.sptech.FamiliaConnect.dto.deficiencia.DeficienciaResponseDto;
import school.sptech.FamiliaConnect.model.Deficiencia;

import java.util.List;

public class DeficienciaMapper {

    public static Deficiencia toModel(DeficienciaRequestDto deficienciaRequestDto){

        Deficiencia deficiencia = new Deficiencia();
        deficiencia.setNome(deficienciaRequestDto.getNome());

        return deficiencia;

    }

    public static DeficienciaResponseDto toResponse(Deficiencia deficiencia){

        DeficienciaResponseDto dto = new DeficienciaResponseDto();
        dto.setId(deficiencia.getId());
        dto.setNome(deficiencia.getNome());

        return dto;

    }

    public static List<DeficienciaResponseDto> toResponse(List<Deficiencia> deficiencias){

        return deficiencias.stream()
                .map(DeficienciaMapper::toResponse)
                .toList();

    }

}

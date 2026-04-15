package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.Auditoria.AuditoriaRequestDto;
import school.sptech.FamiliaConnect.dto.Auditoria.AuditoriaResponseDto;
import school.sptech.FamiliaConnect.model.Auditoria;

import java.util.List;

public class AuditoriaMapper {

    public static Auditoria toModel(AuditoriaRequestDto dto) {

        Auditoria auditoria = new Auditoria();
        auditoria.setDadoAntigo(dto.getDadoAntigo());
        auditoria.setDadoNovo(dto.getDadoNovo());
        auditoria.setCreatedAt(dto.getCreatedAt());
        auditoria.setTipoLog(dto.getTipoLog());

        return auditoria;
    }

    public static AuditoriaResponseDto toResponse(Auditoria auditoria) {

        AuditoriaResponseDto dto = new AuditoriaResponseDto();

        dto.setId(auditoria.getId());
        dto.setTipoLog(dto.getTipoLog());
        dto.setDadoAntigo(dto.getDadoAntigo());
        dto.setDadoNovo(dto.getDadoNovo());
        dto.setCreatedAt(dto.getCreatedAt());
        dto.setFuncionarioId(dto.getFuncionarioId());
        dto.setFuncionarioNome(dto.getFuncionarioNome());

        return dto;
    }

    public static List<AuditoriaResponseDto> toResponse(List<Auditoria> auditorias) {
        return auditorias.stream()
                .map(AuditoriaMapper::toResponse)
                .toList();
    }

}

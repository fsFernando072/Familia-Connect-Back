package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.Auditoria.AuditoriaRequestDto;
import school.sptech.FamiliaConnect.dto.Auditoria.AuditoriaResponseDto;
import school.sptech.FamiliaConnect.model.Auditoria;
import school.sptech.FamiliaConnect.model.Funcionario;

import java.util.List;

public class AuditoriaMapper {

    public static Auditoria toModel(AuditoriaRequestDto dto, Funcionario funcionario) {
        return new Auditoria(
                dto.getTipoLog(),
                dto.getAcao(),
                dto.getAcaoAntigo(),
                dto.getAcaoNovo(),
                dto.getCreatedAt(),
                funcionario
        );
    }

    public static AuditoriaResponseDto toResponse(Auditoria auditoria) {
        return new AuditoriaResponseDto(
                auditoria.getId(),
                auditoria.getTipoLog(),
                auditoria.getDadoAntigo(),
                auditoria.getDadoNovo(),
                auditoria.getCreatedAt(),
                auditoria.getFuncionario().getId(),
                auditoria.getFuncionario().getNome()
        );
    }

    public static List<AuditoriaResponseDto> toResponseList(List<Auditoria> auditorias) {
        return auditorias.stream()
                .map(AuditoriaMapper::toResponse)
                .toList();
    }

}

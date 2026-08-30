package school.sptech.FamiliaConnect.infraestructure.web.mapper;

import school.sptech.FamiliaConnect.infraestructure.web.dto.auditoria.AuditoriaRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.auditoria.AuditoriaResponseDto;
import school.sptech.FamiliaConnect.domain.entity.Auditoria;
import school.sptech.FamiliaConnect.domain.entity.Funcionario;

import java.util.List;

public class AuditoriaMapper {

    public static Auditoria toModel(AuditoriaRequestDto dto) {

        Auditoria auditoria = new Auditoria();
        auditoria.setDadoAntigo(dto.getDadoAntigo());
        auditoria.setDadoNovo(dto.getDadoNovo());
        auditoria.setCreatedAt(dto.getCreatedAt());
        auditoria.setTipoLog(dto.getTipoLog());

        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.getFuncionarioId());

        auditoria.setFuncionario(funcionario);

        return auditoria;
    }

    public static AuditoriaResponseDto toResponse(Auditoria auditoria) {

        AuditoriaResponseDto dto = new AuditoriaResponseDto();

        dto.setId(auditoria.getId());
        dto.setTipoLog(auditoria.getTipoLog());
        dto.setDadoAntigo(auditoria.getDadoAntigo());
        dto.setDadoNovo(auditoria.getDadoNovo());
        dto.setCreatedAt(auditoria.getCreatedAt());

        if(auditoria.getFuncionario() != null){
            dto.setFuncionarioId(auditoria.getFuncionario().getId());
            dto.setFuncionarioNome(auditoria.getFuncionario().getNome());
        }

        return dto;
    }

    public static List<AuditoriaResponseDto> toResponse(List<Auditoria> auditorias) {
        return auditorias.stream()
                .map(AuditoriaMapper::toResponse)
                .toList();
    }

}

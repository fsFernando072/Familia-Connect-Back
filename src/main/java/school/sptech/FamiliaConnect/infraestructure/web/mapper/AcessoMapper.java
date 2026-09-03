package school.sptech.FamiliaConnect.infraestructure.web.mapper;

import school.sptech.FamiliaConnect.infraestructure.web.dto.acesso.AcessoRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.acesso.AcessoResponseDto;
import school.sptech.FamiliaConnect.domain.entity.Acesso;

import java.util.List;

public class AcessoMapper {

    public static Acesso toModel(AcessoRequestDto acessoRequestDto) {

        Acesso acesso = new Acesso();
        acesso.setNomeTela(acessoRequestDto.getNomeTela());

        return acesso;
    }

    public static AcessoResponseDto toResponse(Acesso acesso) {

        AcessoResponseDto dto = new AcessoResponseDto();
        dto.setId(acesso.getId());
        dto.setNomeTela(acesso.getNomeTela());

        return dto;
    }

    public static List<AcessoResponseDto> toResponse(List<Acesso> acessos) {

        return acessos.stream()
                .map(AcessoMapper::toResponse)
                .toList();
    }

}

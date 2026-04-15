package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.Acesso.AcessoRequestDto;
import school.sptech.FamiliaConnect.dto.Acesso.AcessoResponseDto;
import school.sptech.FamiliaConnect.model.Acesso;

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

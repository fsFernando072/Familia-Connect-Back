package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.Acesso.AcessoRequestDto;
import school.sptech.FamiliaConnect.dto.Acesso.AcessoResponseDto;
import school.sptech.FamiliaConnect.model.Acesso;

import java.util.List;

public class AcessoMapper {

    public static Acesso toModel(AcessoRequestDto acessoRequestDto) {

        Acesso acesso = new Acesso(
                acessoRequestDto.getNomeTela()
        );

        return acesso;
    }

    public static AcessoResponseDto toResponse(Acesso acesso) {

        AcessoResponseDto acessoResponseDto = new AcessoResponseDto(
                acesso.getNomeTela(),
                acesso.getId()
        );

        return acessoResponseDto;
    }

    public static List<AcessoResponseDto> toResponseList(List<Acesso> acessos) {

        return acessos.stream()
                .map(AcessoMapper::toResponse)
                .toList();
    }

}

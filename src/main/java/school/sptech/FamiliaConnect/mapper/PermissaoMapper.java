package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.Permissao.PermissaoRequestDto;
import school.sptech.FamiliaConnect.dto.Permissao.PermissaoResponseDto;
import school.sptech.FamiliaConnect.model.Permissao;

import java.util.List;

public class PermissaoMapper {

    public static Permissao toModel(PermissaoRequestDto permissaoRequestDto) {

        Permissao permissao = new Permissao(
                permissaoRequestDto.getNome()
        );

        return permissao;
    }

    public static PermissaoResponseDto toResponse(Permissao permissao) {

        PermissaoResponseDto permissaoResponseDto = new PermissaoResponseDto(
                permissao.getNome(),
                permissao.getId()
        );

        return permissaoResponseDto;
    }

    public static List<PermissaoResponseDto> toResponseList(List<Permissao> permissoes) {

        return permissoes.stream()
                .map(PermissaoMapper::toResponse)
                .toList();
    }
}

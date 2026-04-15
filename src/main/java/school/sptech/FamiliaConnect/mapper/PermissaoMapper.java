package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.Permissao.PermissaoRequestDto;
import school.sptech.FamiliaConnect.dto.Permissao.PermissaoResponseDto;
import school.sptech.FamiliaConnect.model.Permissao;

import java.util.List;

public class PermissaoMapper {

    public static Permissao toModel(PermissaoRequestDto permissaoRequestDto) {

        Permissao permissao = new Permissao();
        permissao.setNome(permissaoRequestDto.getNome());

        return permissao;
    }

    public static PermissaoResponseDto toResponse(Permissao permissao) {

        PermissaoResponseDto dto = new PermissaoResponseDto();
        dto.setId(permissao.getId());
        dto.setNome(permissao.getNome());

        return dto;
    }

    public static List<PermissaoResponseDto> toResponse(List<Permissao> permissoes) {

        return permissoes.stream()
                .map(PermissaoMapper::toResponse)
                .toList();
    }
}

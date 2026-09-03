package school.sptech.FamiliaConnect.infraestructure.web.mapper;

import school.sptech.FamiliaConnect.infraestructure.web.dto.profissao.ProfissaoRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.profissao.ProfissaoResponseDto;
import school.sptech.FamiliaConnect.domain.entity.Profissao;

import java.util.List;

public class ProfissaoMapper {

    public static Profissao toModel(ProfissaoRequestDto profissaoRequestDto){

        Profissao profissao = new Profissao();
        profissao.setNome(profissaoRequestDto.getNome());

        return profissao;

    }

    public static ProfissaoResponseDto toResponse(Profissao profissao){

        ProfissaoResponseDto dto = new ProfissaoResponseDto();
        dto.setId(profissao.getId());
        dto.setNome(profissao.getNome());

        return dto;

    }

    public static List<ProfissaoResponseDto> toResponse(List<Profissao> profissoes){

        return profissoes.stream()
                .map(ProfissaoMapper::toResponse)
                .toList();

    }

}

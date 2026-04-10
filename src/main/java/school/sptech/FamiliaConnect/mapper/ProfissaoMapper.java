package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.profissao.ProfissaoRequestDto;
import school.sptech.FamiliaConnect.dto.profissao.ProfissaoResponseDto;
import school.sptech.FamiliaConnect.model.Profissao;

import java.util.List;

public class ProfissaoMapper {

    public static Profissao toModel(ProfissaoRequestDto profissaoRequestDto){

        Profissao profissao = new Profissao(
                profissaoRequestDto.getNome()
        );

        return profissao;

    }

    public static ProfissaoResponseDto toResponse(Profissao profissao){

        ProfissaoResponseDto profissaoResponseDto = new ProfissaoResponseDto(
                profissao.getId(),
                profissao.getNome()
        );

        return profissaoResponseDto;

    }

    public static List<ProfissaoResponseDto> toResponseList(List<Profissao> profissoes){

        return profissoes.stream()
                .map(ProfissaoMapper::toResponse)
                .toList();

    }

}

package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.entrega.EntregaRequestDto;
import school.sptech.FamiliaConnect.dto.entrega.EntregaResponseDto;
import school.sptech.FamiliaConnect.model.Entrega;
import school.sptech.FamiliaConnect.model.Funcionario;
import school.sptech.FamiliaConnect.model.Pessoa;

import java.util.List;

public class EntregaMapper {

    public static Entrega toModel(EntregaRequestDto dto, Funcionario funcionario, Pessoa pessoa) {

        Entrega entrega = new Entrega();
        entrega.setFuncionario(funcionario);
        entrega.setPessoa(pessoa);

        return entrega;
    }

    public static EntregaResponseDto toResponse(Entrega entrega) {

        EntregaResponseDto dto = new EntregaResponseDto();

        dto.setId(entrega.getId());
        dto.setDataEntrega(dto.getDataEntrega());
        dto.setFuncionarioId(dto.getFuncionarioId());
        dto.setPessoaId(dto.getPessoaId());

        return dto;
    }

    public static List<EntregaResponseDto> toResponse(List<Entrega> entregas) {
        return entregas.stream()
                .map(EntregaMapper::toResponse)
                .toList();
    }

}

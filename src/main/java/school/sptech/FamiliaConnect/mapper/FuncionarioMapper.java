package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.funcionario.FuncionarioRequestDto;
import school.sptech.FamiliaConnect.dto.funcionario.FuncionarioResponseDto;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.model.Funcionario;

import java.util.List;

public class FuncionarioMapper {

    public static Funcionario toModel(FuncionarioRequestDto dto, Cargo cargo) {

        Funcionario funcionario = new Funcionario();
        funcionario.setCargo(cargo);

        return funcionario;
    }

    public static FuncionarioResponseDto toResponse(Funcionario funcionario) {

        FuncionarioResponseDto dto = new FuncionarioResponseDto();

        dto.setId(dto.getId());
        dto.setNome(dto.getNome());
        dto.setCpf(dto.getCpf());
        dto.setSenha(dto.getSenha());
        dto.setFotoFuncionario(dto.getFotoFuncionario());
        dto.setCargo(dto.getCargo());

        return dto;
    }

    public static List<FuncionarioResponseDto> toResponse(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(FuncionarioMapper::toResponse)
                .toList();
    }

}

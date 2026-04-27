package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.funcionario.*;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.model.Funcionario;

import java.util.List;

public class FuncionarioMapper {

    public static Funcionario toModel(FuncionarioRequestDto dto) {

        Funcionario funcionario = new Funcionario(
            dto.getNome(),
                dto.getCpf(),
                dto.getSenha(),
                dto.getFotoFuncionario()
        );

        return funcionario;

    }

    public static FuncionarioResponseDto toResponse(Funcionario funcionario) {

        FuncionarioResponseDto.FuncionarioCargo funcionarioCargo = new FuncionarioResponseDto.FuncionarioCargo(
                funcionario.getCargo().getId(),
                funcionario.getCargo().getNome()
        );

        FuncionarioResponseDto funcionarioResponseDto = new FuncionarioResponseDto(
            funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getSenha(),
                funcionario.getFoto_funcionario(),
                funcionarioCargo
        );

        return funcionarioResponseDto;

    }

    public static List<FuncionarioResponseDto> toResponse(List<Funcionario> funcionarios) {

        return funcionarios.stream()
                .map(FuncionarioMapper::toResponse)
                .toList();

    }

    public static Funcionario of(FuncionarioLoginDto usuarioLoginDto) {

        Funcionario usuario = new Funcionario(
                usuarioLoginDto.getCpf(),
                usuarioLoginDto.getSenha()
        );

        return usuario;
    }

    public static FuncionarioSessaoDto ofSessao(FuncionarioTokenDto tokenDto) {
        FuncionarioSessaoDto dto = new FuncionarioSessaoDto();

        dto.setId(tokenDto.getId());
        dto.setCpf(tokenDto.getCpf());
        dto.setNome(tokenDto.getNome());

        return dto;
    }

    public static FuncionarioTokenDto of(Funcionario funcionario, String token) {
        FuncionarioTokenDto funcionarioTokenDto = new FuncionarioTokenDto();

        funcionarioTokenDto.setId(funcionario.getId());
        funcionarioTokenDto.setCpf(funcionario.getCpf());
        funcionarioTokenDto.setNome(funcionario.getNome());
        funcionarioTokenDto.setToken(token);

        return funcionarioTokenDto;
    }

}

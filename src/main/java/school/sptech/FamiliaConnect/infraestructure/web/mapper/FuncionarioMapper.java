package school.sptech.FamiliaConnect.infraestructure.web.mapper;

import school.sptech.FamiliaConnect.domain.entity.Cargo;
import school.sptech.FamiliaConnect.domain.entity.Funcionario;
import school.sptech.FamiliaConnect.infraestructure.web.dto.funcionario.*;

import java.util.List;

public class FuncionarioMapper {

    public static Funcionario toModel(FuncionarioRequestDto dto) {

        Funcionario funcionario = new Funcionario(
                dto.getNome(),
                dto.getCpf(),
                dto.getSenha(),
                null
        );

        Cargo cargo = new Cargo();
        cargo.setId(dto.getCargoId());

        funcionario.setCargo(cargo);

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
                funcionario.getFotoUrl(),
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

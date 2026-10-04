package school.sptech.FamiliaConnect.infraestructure.web.mapper;

import school.sptech.FamiliaConnect.domain.entity.CargoPermissao;
import school.sptech.FamiliaConnect.infraestructure.web.dto.cargo.CargoPermissaoDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.cargo.CargoRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.cargo.CargoResponseDto;
import school.sptech.FamiliaConnect.domain.entity.Cargo;

import java.util.ArrayList;
import java.util.List;

public class CargoMapper {

    public static Cargo toModel(CargoRequestDto dto) {
        Cargo cargo = new Cargo();
        cargo.setNome(dto.getNome());
        cargo.setDescricao(dto.getDescricao());

        List<CargoPermissao> permissoes = new ArrayList<>();

        if (dto.getPermissoes() != null) {
            for (CargoPermissaoDto permissaoDto : dto.getPermissoes()) {
                permissoes.add(new CargoPermissao(cargo, permissaoDto.getPagina(), permissaoDto.getNivel()));
            }
        }

        cargo.setPermissoes(permissoes);

        return cargo;
    }

    public static CargoResponseDto toResponse(Cargo cargo) {
        CargoResponseDto dto = new CargoResponseDto();
        dto.setId(cargo.getId());
        dto.setNome(cargo.getNome());
        dto.setDescricao(cargo.getDescricao());

        List<CargoPermissaoDto> permissoes = cargo.getPermissoes() == null
                ? List.of()
                : cargo.getPermissoes().stream()
                        .map(permissao -> new CargoPermissaoDto(permissao.getPagina(), permissao.getNivel()))
                        .toList();

        dto.setPermissoes(permissoes);

        return dto;
    }

    public static List<CargoResponseDto> toResponse(List<Cargo> cargos) {
        return cargos.stream()
                .map(CargoMapper::toResponse)
                .toList();
    }
}

package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.cargo.CargoRequestDto;
import school.sptech.FamiliaConnect.dto.cargo.CargoResponseDto;
import school.sptech.FamiliaConnect.model.Cargo;

import java.util.List;

public class CargoMapper {

    public static Cargo toModel(CargoRequestDto dto) {
        Cargo cargo = new Cargo();
        cargo.setNome(dto.getNome());
        cargo.setDescricao(dto.getDescricao());

        return cargo;
    }

    public static CargoResponseDto toResponse(Cargo cargo) {
        CargoResponseDto dto = new CargoResponseDto();
        dto.setId(cargo.getId());
        dto.setNome(cargo.getNome());
        dto.setDescricao(cargo.getDescricao());

        return dto;
    }

    public static List<CargoResponseDto> toResponse(List<Cargo> cargos) {
        return cargos.stream()
                .map(CargoMapper::toResponse)
                .toList();
    }
}
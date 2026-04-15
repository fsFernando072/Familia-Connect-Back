package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.Cargo.CargoRequestDto;
import school.sptech.FamiliaConnect.dto.Cargo.CargoResponseDto;
import school.sptech.FamiliaConnect.model.Cargo;

import java.util.List;

public class CargoMapper {

    public static Cargo toModel(CargoRequestDto dto) {
        Cargo cargo = new Cargo();
        cargo.setNome(dto.getNome());

        return cargo;
    }

    public static CargoResponseDto toResponse(Cargo cargo) {
        CargoResponseDto dto = new CargoResponseDto();
        dto.setId(cargo.getId());
        dto.setNome(cargo.getNome());

        return dto;
    }

    public static List<CargoResponseDto> toResponse(List<Cargo> cargos) {
        return cargos.stream()
                .map(CargoMapper::toResponse)
                .toList();
    }
}
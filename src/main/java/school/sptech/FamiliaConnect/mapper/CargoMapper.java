package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.Cargo.CargoRequestDto;
import school.sptech.FamiliaConnect.dto.Cargo.CargoResponseDto;
import school.sptech.FamiliaConnect.model.Cargo;

import java.util.List;

public class CargoMapper {

    public static Cargo toModel(CargoRequestDto dto) {
        return new Cargo(
                dto.getNome()
        );
    }

    public static CargoResponseDto toResponse(Cargo cargo) {
        return new CargoResponseDto(
                cargo.getNome(),
                cargo.getIdCargo()
        );
    }

    public static List<CargoResponseDto> toResponseList(List<Cargo> cargos) {
        return cargos.stream()
                .map(CargoMapper::toResponse)
                .toList();
    }
}
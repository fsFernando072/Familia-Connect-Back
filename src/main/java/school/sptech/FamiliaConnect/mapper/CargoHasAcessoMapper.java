package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.CargoHasAcesso.CargoHasAcessoRequestDto;
import school.sptech.FamiliaConnect.dto.CargoHasAcesso.CargoHasAcessoResponseDto;
import school.sptech.FamiliaConnect.model.Acesso;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.model.CargoHasAcesso;

import java.util.List;

public class CargoHasAcessoMapper {

    public static CargoHasAcesso toModel(CargoHasAcessoRequestDto dto) {
        CargoHasAcesso cargoHasAcesso = new CargoHasAcesso();

        Cargo cargo = new Cargo();
        cargo.setId(dto.getCargoId());

        Acesso acesso = new Acesso();
        acesso.setId(dto.getAcessoId());

        cargoHasAcesso.setCargo(cargo);
        cargoHasAcesso.setAcesso(acesso);


        return cargoHasAcesso;
    }

    public static CargoHasAcessoResponseDto toResponse(CargoHasAcesso entity) {
        Cargo cargoEntidade = entity.getCargo();
        CargoHasAcessoResponseDto.Cargo cargo = new CargoHasAcessoResponseDto.Cargo();
        cargo.setId(cargoEntidade.getId());
        cargo.setNome(cargoEntidade.getNome());

        Acesso acessoEntidade = entity.getAcesso();
        CargoHasAcessoResponseDto.Acesso acesso = new CargoHasAcessoResponseDto.Acesso();
        acesso.setId(acessoEntidade.getId());
        acesso.setNomeTela(acessoEntidade.getNomeTela());


        CargoHasAcessoResponseDto dto = new CargoHasAcessoResponseDto();

        dto.setId(entity.getId());
        dto.setCargo(cargo);
        dto.setAcesso(acesso);

        return dto;
    }

    public static List<CargoHasAcessoResponseDto> toResponse(List<CargoHasAcesso> cargosHasAcesso) {
        return cargosHasAcesso.stream()
                .map(CargoHasAcessoMapper::toResponse)
                .toList();
    }

}

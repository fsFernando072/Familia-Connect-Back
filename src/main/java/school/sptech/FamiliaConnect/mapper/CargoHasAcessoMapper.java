package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.CargoHasAcesso.CargoHasAcessoResponseDto;
import school.sptech.FamiliaConnect.model.Acesso;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.model.CargoHasAcesso;
import school.sptech.FamiliaConnect.model.Permissao;

import java.util.List;

public class CargoHasAcessoMapper {

    public static CargoHasAcesso toModel(Cargo cargo, Acesso acesso, Permissao permissao) {
        return new CargoHasAcesso(
                cargo,
                acesso,
                permissao
        );
    }

    public static CargoHasAcessoResponseDto toResponse(CargoHasAcesso cargoHasAcesso) {
        return new CargoHasAcessoResponseDto(
                cargoHasAcesso.getId(),
                cargoHasAcesso.getCargo().getIdCargo(),
                cargoHasAcesso.getCargo().getNome(),
                cargoHasAcesso.getAcesso().getId(),
                cargoHasAcesso.getAcesso().getNomeTela(),
                cargoHasAcesso.getPermissao().getId(),
                cargoHasAcesso.getPermissao().getNome()
        );
    }

    public static List<CargoHasAcessoResponseDto> toResponseList(List<CargoHasAcesso> cargosHasAcesso) {
        return cargosHasAcesso.stream()
                .map(CargoHasAcessoMapper::toResponse)
                .toList();
    }

}

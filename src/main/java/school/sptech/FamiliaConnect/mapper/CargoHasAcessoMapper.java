package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.CargoHasAcesso.CargoHasAcessoResponseDto;
import school.sptech.FamiliaConnect.model.Acesso;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.model.CargoHasAcesso;
import school.sptech.FamiliaConnect.model.Permissao;

import java.util.List;

public class CargoHasAcessoMapper {

    public static CargoHasAcesso toModel(Cargo cargo, Acesso acesso, Permissao permissao) {
         CargoHasAcesso cargoHasAcesso = new CargoHasAcesso();
         cargoHasAcesso.setCargo(cargo);
         cargoHasAcesso.setAcesso(acesso);
         cargoHasAcesso.setPermissao(permissao);

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

        Permissao permissaoEntidade = entity.getPermissao();
        CargoHasAcessoResponseDto.Permissao permissao = new CargoHasAcessoResponseDto.Permissao();
        permissao.setId(permissaoEntidade.getId());
        permissao.setNome(permissaoEntidade.getNome());

        CargoHasAcessoResponseDto dto = new CargoHasAcessoResponseDto();

        dto.setId(entity.getId());
        dto.setCargo(cargo);
        dto.setAcesso(acesso);
        dto.setPermissao(permissao);

        return dto;
    }

    public static List<CargoHasAcessoResponseDto> toResponse(List<CargoHasAcesso> cargosHasAcesso) {
        return cargosHasAcesso.stream()
                .map(CargoHasAcessoMapper::toResponse)
                .toList();
    }

}

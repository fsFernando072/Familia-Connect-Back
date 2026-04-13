package school.sptech.FamiliaConnect.dto.CargoHasAcesso;

import jakarta.validation.constraints.NotNull;

public class CargoHasAcessoRequestDto {

    @NotNull
    private Integer cargoId;

    @NotNull
    private Integer acessoId;

    @NotNull
    private Integer permissaoId;

    public CargoHasAcessoRequestDto() {}

    public CargoHasAcessoRequestDto(Integer cargoId, Integer acessoId, Integer permissaoId) {
        this.cargoId = cargoId;
        this.acessoId = acessoId;
        this.permissaoId = permissaoId;
    }

    public Integer getCargoId() {
        return cargoId;
    }

    public void setCargoId(Integer cargoId) {
        this.cargoId = cargoId;
    }

    public Integer getAcessoId() {
        return acessoId;
    }

    public void setAcessoId(Integer acessoId) {
        this.acessoId = acessoId;
    }

    public Integer getPermissaoId() {
        return permissaoId;
    }

    public void setPermissaoId(Integer permissaoId) {
        this.permissaoId = permissaoId;
    }

}

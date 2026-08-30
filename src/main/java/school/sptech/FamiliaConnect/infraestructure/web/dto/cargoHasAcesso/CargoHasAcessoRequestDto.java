package school.sptech.FamiliaConnect.infraestructure.web.dto.cargoHasAcesso;

import jakarta.validation.constraints.NotNull;

public class CargoHasAcessoRequestDto {

    @NotNull
    private Integer cargoId;

    @NotNull
    private Integer acessoId;


    public CargoHasAcessoRequestDto() {}

    public CargoHasAcessoRequestDto(Integer cargoId, Integer acessoId) {
        this.cargoId = cargoId;
        this.acessoId = acessoId;
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


}

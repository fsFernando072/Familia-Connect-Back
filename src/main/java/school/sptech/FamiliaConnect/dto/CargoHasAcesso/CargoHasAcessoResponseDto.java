package school.sptech.FamiliaConnect.dto.CargoHasAcesso;

public class CargoHasAcessoResponseDto {

    private Integer id;

    private Integer cargoId;
    private String cargoNome;

    private Integer acessoId;
    private String acessoNomeTela;

    private Integer permissaoId;
    private String permissaoNome;

    public CargoHasAcessoResponseDto() {}

    public CargoHasAcessoResponseDto(Integer id,
                                     Integer cargoId,
                                     String cargoNome,
                                     Integer acessoId,
                                     String acessoNomeTela,
                                     Integer permissaoId,
                                     String permissaoNome) {
        this.id = id;
        this.cargoId = cargoId;
        this.cargoNome = cargoNome;
        this.acessoId = acessoId;
        this.acessoNomeTela = acessoNomeTela;
        this.permissaoId = permissaoId;
        this.permissaoNome = permissaoNome;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCargoId() {
        return cargoId;
    }

    public String getCargoNome() {
        return cargoNome;
    }

    public Integer getAcessoId() {
        return acessoId;
    }

    public String getAcessoNomeTela() {
        return acessoNomeTela;
    }

    public Integer getPermissaoId() {
        return permissaoId;
    }

    public String getPermissaoNome() {
        return permissaoNome;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCargoId(Integer cargoId) {
        this.cargoId = cargoId;
    }

    public void setCargoNome(String cargoNome) {
        this.cargoNome = cargoNome;
    }

    public void setAcessoId(Integer acessoId) {
        this.acessoId = acessoId;
    }

    public void setAcessoNomeTela(String acessoNomeTela) {
        this.acessoNomeTela = acessoNomeTela;
    }

    public void setPermissaoId(Integer permissaoId) {
        this.permissaoId = permissaoId;
    }

    public void setPermissaoNome(String permissaoNome) {
        this.permissaoNome = permissaoNome;
    }

}

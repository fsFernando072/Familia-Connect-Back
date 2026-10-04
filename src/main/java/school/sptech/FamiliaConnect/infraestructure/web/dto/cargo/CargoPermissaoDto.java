package school.sptech.FamiliaConnect.infraestructure.web.dto.cargo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import school.sptech.FamiliaConnect.domain.enums.NivelAcessoEnum;
import school.sptech.FamiliaConnect.domain.enums.PaginaEnum;

@Schema(description = "Permissão de um cargo sobre uma página")
public class CargoPermissaoDto {

    @Schema(description = "Página do sistema", example = "PRODUTOS")
    @NotNull(message = "A página é obrigatória")
    private PaginaEnum pagina;

    @Schema(description = "Nível de acesso na página", example = "LISTAS_CADASTROS")
    @NotNull(message = "O nível de acesso é obrigatório")
    private NivelAcessoEnum nivel;

    public CargoPermissaoDto() {}

    public CargoPermissaoDto(PaginaEnum pagina, NivelAcessoEnum nivel) {
        this.pagina = pagina;
        this.nivel = nivel;
    }

    public PaginaEnum getPagina() {
        return pagina;
    }

    public void setPagina(PaginaEnum pagina) {
        this.pagina = pagina;
    }

    public NivelAcessoEnum getNivel() {
        return nivel;
    }

    public void setNivel(NivelAcessoEnum nivel) {
        this.nivel = nivel;
    }
}

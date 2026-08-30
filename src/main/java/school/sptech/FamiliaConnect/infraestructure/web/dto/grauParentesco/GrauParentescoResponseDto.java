package school.sptech.FamiliaConnect.infraestructure.web.dto.grauParentesco;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados do grau de parentesco")
public class GrauParentescoResponseDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "ID do grau de parentesco")
    private Integer id;

    @Schema(description = "Nome do grau de parentesco")
    private String grau;

    // Construtores ----------------------------------------------------------------------------------------------------

    public GrauParentescoResponseDto() {
    }

    public GrauParentescoResponseDto(Integer id, String grau) {
        this.id = id;
        this.grau = grau;
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }
}

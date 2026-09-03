package school.sptech.FamiliaConnect.infraestructure.web.dto.categoria;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class CategoriaRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "Nome da categoria")
    @NotBlank(message = "Nome da categoria é obrigatório")
    private String nome;

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

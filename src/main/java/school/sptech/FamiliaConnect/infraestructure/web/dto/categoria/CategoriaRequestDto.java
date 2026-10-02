package school.sptech.FamiliaConnect.infraestructure.web.dto.categoria;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "Nome da categoria")
    @NotBlank(message = "Nome da categoria é obrigatório")
    @Size(min = 3, max = 45, message = "O tamanho do nome da categoria deve estar entre 3 e 45")
    private String nome;

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

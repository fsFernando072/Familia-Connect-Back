package school.sptech.FamiliaConnect.infraestructure.web.dto.acesso;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AcessoRequestDto {

    @Schema(description = "Nome da tela")
    @NotBlank(message = "Nome da tela é obrigatória")
    @Size(min = 3, max = 45, message = "O tamanho do nome da tela deve estar entre 3 e 45")
    private String nomeTela;

    public AcessoRequestDto() {}

    public AcessoRequestDto(String nomeTela) {
        this.nomeTela = nomeTela;
    }

    public String getNomeTela() {
        return nomeTela;
    }

    public void setNomeTela(String nomeTela) {
        this.nomeTela = nomeTela;
    }
}

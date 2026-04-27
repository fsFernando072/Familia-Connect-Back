package school.sptech.FamiliaConnect.dto.Acesso;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class AcessoRequestDto {

    @Schema(description = "Nome da tela")
    @NotBlank(message = "Nome da tela é obrigatória")
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

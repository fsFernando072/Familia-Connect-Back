package school.sptech.FamiliaConnect.dto.Permissao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class PermissaoRequestDto {

    @Schema(description = "Nome da permissão")
    @NotBlank(message = "Nome da permissão é obrigatório")
    private String nome;

    public PermissaoRequestDto() {}

    public PermissaoRequestDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

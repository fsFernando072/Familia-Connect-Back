package school.sptech.FamiliaConnect.dto.Cargo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class CargoRequestDto {

    @Schema(description = "Nome do cargo")
    @NotBlank(message = "Nome do cargo é obrigatório")
    private String nome;

    public CargoRequestDto() {}

    public CargoRequestDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

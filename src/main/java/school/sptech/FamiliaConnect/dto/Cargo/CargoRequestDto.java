package school.sptech.FamiliaConnect.dto.Cargo;

import jakarta.validation.constraints.NotBlank;

public class CargoRequestDto {

    @NotBlank
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

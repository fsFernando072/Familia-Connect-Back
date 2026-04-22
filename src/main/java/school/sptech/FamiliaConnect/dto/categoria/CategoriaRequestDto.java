package school.sptech.FamiliaConnect.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public class CategoriaRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @NotBlank
    private String nome;

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

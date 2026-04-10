package school.sptech.FamiliaConnect.dto.deficiencia;

import jakarta.validation.constraints.NotBlank;

public class DeficienciaRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @NotBlank
    private String nome;

    // Construtores ----------------------------------------------------------------------------------------------------

    public DeficienciaRequestDto(){}

    public DeficienciaRequestDto(String nome) {
        this.nome = nome;
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

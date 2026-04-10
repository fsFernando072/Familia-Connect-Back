package school.sptech.FamiliaConnect.dto.deficiencia;

import jakarta.validation.constraints.NotBlank;

public class DeficienciaResponseDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private Integer id;
    private String nome;

    // Construtores ----------------------------------------------------------------------------------------------------

    public DeficienciaResponseDto(){}

    public DeficienciaResponseDto(String nome, Integer id) {
        this.nome = nome;
        this.id = id;
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

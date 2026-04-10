package school.sptech.FamiliaConnect.dto.profissao;

import jakarta.validation.constraints.NotBlank;

public class ProfissaoRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @NotBlank
    private String nome;

    // Construtores ----------------------------------------------------------------------------------------------------

    public ProfissaoRequestDto(){}

    public ProfissaoRequestDto(String nome) {
        this.nome = nome;
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

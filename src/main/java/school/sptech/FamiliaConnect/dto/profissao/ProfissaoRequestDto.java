package school.sptech.FamiliaConnect.dto.profissao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class ProfissaoRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "Nome da profissão")
    @NotBlank(message = "Nome da profissão é obrigatório")
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

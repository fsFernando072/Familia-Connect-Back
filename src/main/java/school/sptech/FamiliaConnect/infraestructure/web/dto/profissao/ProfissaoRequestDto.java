package school.sptech.FamiliaConnect.infraestructure.web.dto.profissao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProfissaoRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "Nome da profissão")
    @NotBlank(message = "Nome da profissão é obrigatório")
    @Size(min = 3, max = 80, message = "O tamanho da profissão deve estar entre 3 e 80")
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

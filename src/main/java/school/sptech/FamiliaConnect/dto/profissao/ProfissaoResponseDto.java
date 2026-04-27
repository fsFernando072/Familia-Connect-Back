package school.sptech.FamiliaConnect.dto.profissao;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados da profissão")
public class ProfissaoResponseDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "ID da profissão")
    private Integer id;

    @Schema(description = "Nome da profissão")
    private String nome;

    // Construtores ----------------------------------------------------------------------------------------------------

    public ProfissaoResponseDto() {
    }

    public ProfissaoResponseDto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

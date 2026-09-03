package school.sptech.FamiliaConnect.infraestructure.web.dto.categoria;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados da categoria")
public class CategoriaResponseDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "Id da categoria")
    private Integer id;

    @Schema(description = "Nome da categoria")
    private String nome;

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

package school.sptech.FamiliaConnect.infraestructure.web.dto.produto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public class ProdutoRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "Nome do produto")
    @NotBlank(message = "Nome do produto é obrigatório")
    @Size(min = 3, max = 45, message = "O tamanho do nome do produto deve estar entre 3 e 45")
    private String nome;

    @Schema(description = "Descrição do produto")
    @NotBlank(message = "Descrição do produto é obrigatória")
    @Size(min = 3, max = 100, message = "O tamanho da descrição do produto deve estar entre 3 e 100")
    private String descricao;

    @Schema(description = "ID da categoria do produto")
    @NotNull(message = "ID da categoria do produto é obrigatório")
    @Positive(message = "ID da categoria do produto tem que ser positivo")
    private Integer idCategoria;

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
}

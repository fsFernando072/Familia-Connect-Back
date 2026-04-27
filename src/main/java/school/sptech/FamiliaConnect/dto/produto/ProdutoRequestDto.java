package school.sptech.FamiliaConnect.dto.produto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ProdutoRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "Nome do produto")
    @NotBlank(message = "Nome do produto é obrigatório")
    private String nome;

    @Schema(description = "Quantidade do produto")
    @NotNull(message = "Quantidade do produto no estoque é obrigatório")
    @PositiveOrZero(message = "Quantidade do produto no estoque tem que ser maior ou igual a zero")
    private Integer quantidade;

    @Schema(description = "Descrição do produto")
    @NotBlank(message = "Descrição do produto é obrigatória")
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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

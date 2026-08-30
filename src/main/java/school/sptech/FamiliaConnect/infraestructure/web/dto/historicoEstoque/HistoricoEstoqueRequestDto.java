package school.sptech.FamiliaConnect.infraestructure.web.dto.historicoEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class HistoricoEstoqueRequestDto {
    @Schema(description = "Quantidade do produto")
    @NotNull(message = "Quantidade do produto no estoque é obrigatório")
    @PositiveOrZero(message = "Quantidade do produto no estoque tem que ser maior ou igual a zero")
    private Double quantidade;

    @Schema(description = "ID do produto")
    @NotNull(message = "ID do produto é obrigatório")
    @Positive(message = "ID do produto tem que ser positivo")
    private Integer idProduto;

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }
}

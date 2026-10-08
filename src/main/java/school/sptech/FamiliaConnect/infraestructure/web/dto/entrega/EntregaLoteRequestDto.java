package school.sptech.FamiliaConnect.infraestructure.web.dto.entrega;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Schema(description = "Entrega completa de uma família: vários produtos registrados de uma só vez")
public class EntregaLoteRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "Data de entrega (AAAA-MM-DD)")
    @NotBlank(message = "Data de entrega deve ser obrigatória")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Data de entrega deve estar no formato AAAA-MM-DD")
    private String dataEntrega;

    @Schema(description = "ID do funcionário responsável pela entrega")
    @NotNull(message = "ID do funcionário tem que ser obrigatório")
    @Positive(message = "ID do funcionário tem que ser positivo")
    private Integer idFuncionario;

    @Schema(description = "ID da pessoa que recebeu a entrega (responsável da família)")
    @NotNull(message = "ID da pessoa tem que ser obrigatório")
    @Positive(message = "ID da pessoa tem que ser positivo")
    private Integer idPessoa;

    @Schema(description = "Produtos entregues e suas quantidades")
    @NotEmpty(message = "Informe ao menos um item para entregar")
    @Valid
    private List<Item> itens;

    // Inner Classes ---------------------------------------------------------------------------------------------------

    public static class Item {

        @Schema(description = "ID do produto")
        @NotNull(message = "ID do produto tem que ser obrigatório")
        @Positive(message = "ID do produto tem que ser positivo")
        private Integer idProduto;

        @Schema(description = "Quantidade entregue do produto")
        @NotNull(message = "Quantidade tem que ser obrigatória")
        @Min(value = 1, message = "Quantidade tem que ser no mínimo 1")
        @Max(value = 1000, message = "Quantidade tem que ser no máximo 1000")
        private Integer quantidade;

        public Integer getIdProduto() {
            return idProduto;
        }

        public void setIdProduto(Integer idProduto) {
            this.idProduto = idProduto;
        }

        public Integer getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(Integer quantidade) {
            this.quantidade = quantidade;
        }
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}

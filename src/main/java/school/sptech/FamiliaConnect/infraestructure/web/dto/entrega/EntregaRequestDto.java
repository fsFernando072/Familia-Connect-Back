package school.sptech.FamiliaConnect.infraestructure.web.dto.entrega;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class EntregaRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "ID do funcionário responsável pela entrega")
    @NotNull(message = "ID do funcionário tem que ser obrigatório")
    @Positive(message = "ID do funcionário tem que ser positivo")
    private Integer idFuncionario;

    @Schema(description = "ID da pessoa que recebeu a entrega")
    @NotNull(message = "ID da pessoa tem que ser obrigatório")
    @Positive(message = "ID da pessoa tem que ser positivo")
    private Integer idPessoa;

    @Schema(description = "ID do produto da entrega")
    @NotNull(message = "ID do produto tem que ser obrigatório")
    @Positive(message = "ID do produto tem que ser positivo")
    private Integer idProduto;

    // Getters e Setters -----------------------------------------------------------------------------------------------

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

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }
}

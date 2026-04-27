package school.sptech.FamiliaConnect.dto.entrega;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.CreationTimestamp;
import school.sptech.FamiliaConnect.model.Funcionario;
import school.sptech.FamiliaConnect.model.Pessoa;

import java.time.LocalDate;

public class EntregaRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "Data da entrega")
    @CreationTimestamp
    private LocalDate dataEntrega;

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

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
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

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }
}

package school.sptech.FamiliaConnect.dto.entrega;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.ManyToOne;
import school.sptech.FamiliaConnect.model.Funcionario;
import school.sptech.FamiliaConnect.model.Pessoa;

import java.time.LocalDate;

@Schema(description = "Dados retornados da entrega")
public class EntregaResponseDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "ID da entrega")
    private Integer id;

    @Schema(description = "Data da entrega")
    private LocalDate dataEntrega;

    @Schema(description = "Funcionário responsável pela entrega")
    private EntregaFuncionario entregaFuncionario;

    @Schema(description = "Pessoa que recebeu a entrega")
    private EntregaPessoa entregaPessoa;

    @Schema(description = "Produto da entrega")
    private EntregaProduto entregaProduto;

    // Inner Classes ---------------------------------------------------------------------------------------------------

    public static class EntregaFuncionario{

        private Integer id;
        private String nome;

        public EntregaFuncionario(Integer id, String nome) {
            this.id = id;
            this.nome = nome;
        }

    }

    public static class EntregaPessoa{

        private Integer id;
        private String nome;

        public EntregaPessoa(Integer id, String nome) {
            this.id = id;
            this.nome = nome;
        }

    }

    public static class EntregaProduto{

        private Integer id;
        private String nome;

        public EntregaProduto(Integer id, String nome) {
            this.id = id;
            this.nome = nome;
        }

    }

    // Construtores ----------------------------------------------------------------------------------------------------

    public EntregaResponseDto(Integer id, LocalDate dataEntrega, EntregaFuncionario entregaFuncionario, EntregaPessoa entregaPessoa, EntregaProduto entregaProduto) {
        this.id = id;
        this.dataEntrega = dataEntrega;
        this.entregaFuncionario = entregaFuncionario;
        this.entregaPessoa = entregaPessoa;
        this.entregaProduto = entregaProduto;
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public EntregaFuncionario getEntregaFuncionario() {
        return entregaFuncionario;
    }

    public void setEntregaFuncionario(EntregaFuncionario entregaFuncionario) {
        this.entregaFuncionario = entregaFuncionario;
    }

    public EntregaPessoa getEntregaPessoa() {
        return entregaPessoa;
    }

    public void setEntregaPessoa(EntregaPessoa entregaPessoa) {
        this.entregaPessoa = entregaPessoa;
    }

    public EntregaProduto getEntregaProduto() {
        return entregaProduto;
    }

    public void setEntregaProduto(EntregaProduto entregaProduto) {
        this.entregaProduto = entregaProduto;
    }
}

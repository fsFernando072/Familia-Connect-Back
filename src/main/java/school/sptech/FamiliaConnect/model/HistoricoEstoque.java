package school.sptech.FamiliaConnect.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
public class HistoricoEstoque {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double quantidade;

    @CreationTimestamp
    private LocalDate dataEstoque;

    @ManyToOne
    private Produto produto;

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataEstoque() {
        return dataEstoque;
    }

    public void setDataEstoque(LocalDate dataEstoque) {
        this.dataEstoque = dataEstoque;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}

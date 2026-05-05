package school.sptech.FamiliaConnect.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Entrega {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dataEntrega;

    @ManyToOne
    private Funcionario funcionario;

    @ManyToOne
    private Pessoa pessoa;

    @ManyToOne
    private Produto produto;

    // Construtores ----------------------------------------------------------------------------------------------------

    public Entrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Entrega() {
    }

// Getters e Setters -----------------------------------------------------------------------------------------------

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}

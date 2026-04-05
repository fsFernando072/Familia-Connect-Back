package school.sptech.FamiliaConnect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Entrega {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    private Integer id;
    private LocalDate dataEntrega;
    private Funcionario funcionario;
    private Pessoa pessoa;

    // Construtores ----------------------------------------------------------------------------------------------------

    public Entrega() {
    }

    public Entrega(LocalDate dataEntrega, Funcionario funcionario, Integer id, Pessoa pessoa) {
        this.dataEntrega = dataEntrega;
        this.funcionario = funcionario;
        this.id = id;
        this.pessoa = pessoa;
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
}

package school.sptech.FamiliaConnect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Funcionario {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    private Integer id;
    private String nome;
    private String cpf;
    private String senha;
    private String foto_funcionario;
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    // Construtores ----------------------------------------------------------------------------------------------------

    public Funcionario() {
    }

    public Funcionario(String caminhoFoto, Cargo cargo, String cpf, Integer id, String nome, String senha) {
        this.foto_funcionario = caminhoFoto;
        this.cargo = cargo;
        this.cpf = cpf;
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public String getFoto_funcionario() {
        return foto_funcionario;
    }

    public void setFoto_funcionario(String foto_funcionario) {
        this.foto_funcionario = foto_funcionario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

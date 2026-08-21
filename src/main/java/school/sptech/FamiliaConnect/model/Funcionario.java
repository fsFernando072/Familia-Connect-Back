package school.sptech.FamiliaConnect.model;

import jakarta.persistence.*;

@Entity
public class Funcionario {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private String senha;

    @ManyToOne
    private Arquivo foto;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    // Construtores ----------------------------------------------------------------------------------------------------

    public Funcionario(String nome, String cpf, String senha, Arquivo foto) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.foto = foto;
    }

    public Funcionario(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Funcionario() {
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public Arquivo getFoto() {
        return foto;
    }

    public void setFoto(Arquivo foto) {
        this.foto = foto;
    }

    // Helper para expor a foto como URL nos DTOs de resposta, sem espalhar essa lógica pelos mappers.
    public String getFotoUrl() {
        return foto != null ? "/arquivos/" + foto.getId() + "/visualizar" : null;
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

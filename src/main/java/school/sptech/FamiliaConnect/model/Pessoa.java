package school.sptech.FamiliaConnect.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Pessoa {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    private Integer id;
    private String nome;
    private String rg;
    private String cpf;
    private LocalDate dtNascimento;
    private Boolean isTrabalhando;
    private Boolean isResponsavel;
    private String grauParentesco;
    private String telefone;

    @ManyToOne
    private Deficiencia deficiencia;

    @ManyToOne
    private Profissao profissao;

    @ManyToOne
    private Familia familia;

    // Construtores ----------------------------------------------------------------------------------------------------

    public Pessoa() {
    }

    public Pessoa(String nome, String rg, String cpf, LocalDate dtNascimento, Boolean isTrabalhando, Boolean isResponsavel, String grauParentesco, String telefone) {
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.dtNascimento = dtNascimento;
        this.isTrabalhando = isTrabalhando;
        this.isResponsavel = isResponsavel;
        this.grauParentesco = grauParentesco;
    }

    public Pessoa(String cpf, Deficiencia deficiencia, LocalDate dtNascimento, Familia familia, String grauParentesco, Integer id, Boolean isResponsavel, Boolean isTrabalhando, String nome, Profissao profissao, String rg, String telefone) {
        this.cpf = cpf;
        this.deficiencia = deficiencia;
        this.dtNascimento = dtNascimento;
        this.familia = familia;
        this.grauParentesco = grauParentesco;
        this.id = id;
        this.isResponsavel = isResponsavel;
        this.isTrabalhando = isTrabalhando;
        this.nome = nome;
        this.profissao = profissao;
        this.rg = rg;
        this.telefone = telefone;
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Deficiencia getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(Deficiencia deficiencia) {
        this.deficiencia = deficiencia;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public String getGrauParentesco() {
        return grauParentesco;
    }

    public void setGrauParentesco(String grauParentesco) {
        this.grauParentesco = grauParentesco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getResponsavel() {
        return isResponsavel;
    }

    public void setResponsavel(Boolean responsavel) {
        isResponsavel = responsavel;
    }

    public Boolean getTrabalhando() {
        return isTrabalhando;
    }

    public void setTrabalhando(Boolean trabalhando) {
        isTrabalhando = trabalhando;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}

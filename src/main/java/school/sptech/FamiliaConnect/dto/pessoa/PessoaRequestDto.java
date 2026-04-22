package school.sptech.FamiliaConnect.dto.pessoa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public class PessoaRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @NotBlank
    @Size(min = 2, max = 150)
    private String nome;

    @NotBlank
    @Size(min = 7, max = 9)
    private String rg;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    private LocalDate dataNascimento;

    @Positive
    private Integer idProfissao;

    @Positive
    @NotNull
    private Integer idFamilia;

    @NotBlank
    @Size(min = 11, max = 11)
    private String telefone;

    @NotNull
    private Boolean isTrabalhando;

    @NotNull
    private Boolean isResponsavel;

    @NotBlank
    private String grauParentesco;

    // Construtores ----------------------------------------------------------------------------------------------------

    public PessoaRequestDto() {
    }

    public PessoaRequestDto(String nome, String rg, String cpf, LocalDate dataNascimento, Integer idProfissao, Integer idFamilia, String telefone, Boolean isTrabalhando, Boolean isResponsavel, String grauParentesco) {
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idProfissao = idProfissao;
        this.idFamilia = idFamilia;
        this.telefone = telefone;
        this.isTrabalhando = isTrabalhando;
        this.isResponsavel = isResponsavel;
        this.grauParentesco = grauParentesco;
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdProfissao() {
        return idProfissao;
    }

    public void setIdProfissao(Integer idProfissao) {
        this.idProfissao = idProfissao;
    }

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getTrabalhando() {
        return isTrabalhando;
    }

    public void setTrabalhando(Boolean trabalhando) {
        isTrabalhando = trabalhando;
    }

    public Boolean getResponsavel() {
        return isResponsavel;
    }

    public void setResponsavel(Boolean responsavel) {
        isResponsavel = responsavel;
    }

    public String getGrauParentesco() {
        return grauParentesco;
    }

    public void setGrauParentesco(String grauParentesco) {
        this.grauParentesco = grauParentesco;
    }
}

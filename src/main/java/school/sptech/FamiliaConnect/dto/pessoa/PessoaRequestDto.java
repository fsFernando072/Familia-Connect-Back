package school.sptech.FamiliaConnect.dto.pessoa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public class PessoaRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "Nome da pessoa")
    @NotBlank(message = "Nome da pessoa é obrigatório")
    private String nome;

    @Schema(description = "RG da pessoa")
    @NotBlank(message = "RG da pessoa é obrigatório")
    @Size(min = 7, max = 9, message = "RG da pessoa tem que ser válido")
    private String rg;

    @Schema(description = "CPF da pessoa")
    @NotBlank(message = "CPF da pessoa é obrigatório")
    @CPF(message = "CPF da pessoa tem que ser válido")
    private String cpf;

    @Schema(description = "Data de nascimento da pessoa")
    @NotBlank(message = "Data de nascimento da pessoa é obrigatório")
    private LocalDate dataNascimento;

    @Schema(description = "ID da profissão da pessoa")
    @NotNull(message = "ID da profissão da pessoa é obrigatório")
    @Positive(message = "ID da profissão da pessoa tem que ser positivo")
    private Integer idProfissao;

    @Schema(description = "ID da família da pessoa")
    @Positive(message = "ID da família da pessoa tem que ser positivo")
    @NotNull(message = "ID da família da pessoa é obrigatório")
    private Integer idFamilia;

    @Schema(description = "Telefone da pessoa")
    @NotBlank(message = "Telefone da pessoa é obrigatório")
    @Size(min = 11, max = 11, message = "Telefone da pessoa tem que ser válido")
    private String telefone;

    @Schema(description = "Se a pessoa trabalha")
    @NotNull(message = "Se a pessoa trabalha é obrigatório")
    private Boolean isTrabalhando;

    @Schema(description = "Se a pessoa é a responsável da família")
    @NotNull(message = "Se a pessoa é responsável é obrigatório")
    private Boolean isResponsavel;

    @Schema(description = "Grau de parentesco da pessoa")
    @NotBlank(message = "Grau de parentesco da pessoa é obrigatório")
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

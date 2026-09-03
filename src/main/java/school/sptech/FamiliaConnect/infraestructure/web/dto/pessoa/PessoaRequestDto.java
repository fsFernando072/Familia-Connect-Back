package school.sptech.FamiliaConnect.infraestructure.web.dto.pessoa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import school.sptech.FamiliaConnect.domain.enums.SexoEnum;

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
    @NotNull(message = "Data de nascimento da pessoa é obrigatório")
    @Past(message = "A data de nascimento deve ser de antes do dia de hoje")
    private LocalDate dataNascimento;

    @Schema(description = "Profissão da pessoa")
    private String profissao;

    @Schema(description = "Sexo da pessoa")
    @NotNull(message = "Sexo da pessoa é obrigatório")
    private SexoEnum sexo;

    @Schema(description = "ID da família da pessoa")
    @Positive(message = "ID da família da pessoa tem que ser positivo")
    private Integer idFamilia;

    @Schema(description = "Telefone da pessoa")
    @NotBlank(message = "Telefone da pessoa é obrigatório")
    @Size(min = 11, max = 11, message = "Telefone da pessoa tem que ser válido")
    private String telefone;

    @Schema(description = "Se a pessoa é a responsável da família")
    @NotNull(message = "Se a pessoa é responsável é obrigatório")
    private Boolean isResponsavel;

    @Schema(description = "Grau de parentesco da pessoa")
    @NotBlank(message = "Grau de parentesco da pessoa é obrigatório")
    private String grauParentesco;

    // Construtores ----------------------------------------------------------------------------------------------------

    public PessoaRequestDto() {
    }

    public PessoaRequestDto(String nome, String rg, String cpf, LocalDate dataNascimento, String profissao, SexoEnum sexo, Integer idFamilia, String telefone, Boolean isResponsavel, String grauParentesco) {
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.profissao = profissao;
        this.sexo = sexo;
        this.idFamilia = idFamilia;
        this.telefone = telefone;
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

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
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

package school.sptech.FamiliaConnect.dto.pessoa;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.FamiliaConnect.model.Pessoa;

import java.time.LocalDate;

@Schema(description = "Dados retornados da pessoa")
public class PessoaResponseDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "ID da pessoa")
    private Integer id;

    @Schema(description = "Nome da pessoa")
    private String nome;

    @Schema(description = "RG da pessoa")
    private String rg;

    @Schema(description = "CPF da pessoa")
    private String cpf;

    @Schema(description = "Data de nascimento da pessoa")
    private LocalDate dataNascimento;

    @Schema(description = "Telefone da pessoa")
    private String telefone;

    @Schema(description = "Se a pessoa está trabalhando")
    private Boolean isTrabalhando;

    @Schema(description = "Se a pessoa é a responsável da família")
    private Boolean isResponsavel;

    @Schema(description = "Grau de parentesco da pessoa")
    private String grauParentesco;

    @Schema(description = "Família da pessoa")
    private PessoaFamilia pessoaFamilia;

    @Schema(description = "Profissão da pessoa")
    private PessoaProfissao pessoaProfissao;

    // Construtores ----------------------------------------------------------------------------------------------------

    public PessoaResponseDto() {
    }

    public PessoaResponseDto(Integer id, String nome, String rg, String cpf, LocalDate dataNascimento, String telefone, Boolean isTrabalhando, Boolean isResponsavel, String grauParentesco) {
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.isTrabalhando = isTrabalhando;
        this.isResponsavel = isResponsavel;
        this.grauParentesco = grauParentesco;
    }

    // Inner Classes ---------------------------------------------------------------------------------------------------

    public static class PessoaFamilia{

        private LocalDate dataCadastro;
        private String foto;

        public LocalDate getDataCadastro() {
            return dataCadastro;
        }

        public void setDataCadastro(LocalDate dataCadastro) {
            this.dataCadastro = dataCadastro;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }
    }

    public static class PessoaProfissao{

        private String nome;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

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

    public void setResponsavel(Boolean isResponsavel) {
        this.isResponsavel = isResponsavel;
    }

    public String getGrauParentesco() {
        return grauParentesco;
    }

    public void setGrauParentesco(String grauParentesco) {
        this.grauParentesco = grauParentesco;
    }

    public PessoaFamilia getPessoaFamilia() {
        return pessoaFamilia;
    }

    public void setPessoaFamilia(PessoaFamilia pessoaFamilia) {
        this.pessoaFamilia = pessoaFamilia;
    }

    public PessoaProfissao getPessoaProfissao() {
        return pessoaProfissao;
    }

    public void setPessoaProfissao(PessoaProfissao pessoaProfissao) {
        this.pessoaProfissao = pessoaProfissao;
    }
}

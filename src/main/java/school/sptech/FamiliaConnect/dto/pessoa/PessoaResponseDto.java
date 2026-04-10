package school.sptech.FamiliaConnect.dto.pessoa;

import school.sptech.FamiliaConnect.model.Pessoa;

import java.time.LocalDate;

public class PessoaResponseDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private String nome;
    private String rg;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private Boolean isTrabalhando;
    private Boolean isResponsavel;
    private String grauParentesco;
    private PessoaFamilia pessoaFamilia;
    private PessoaProfissao pessoaProfissao;
    private PessoaDeficiencia pessoaDeficiencia;

    // Construtores ----------------------------------------------------------------------------------------------------

    public PessoaResponseDto() {
    }

    public PessoaResponseDto(String nome, String rg, String cpf, LocalDate dataNascimento, String telefone, Boolean isTrabalhando, Boolean isResponsavel, String grauParentesco) {
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

        private LocalDate dataCadastroFamilia;
        private String fotoFamilia;

        public PessoaFamilia(String fotoFamilia, LocalDate dataCadastroFamilia) {
            this.fotoFamilia = fotoFamilia;
            this.dataCadastroFamilia = dataCadastroFamilia;
        }

        public LocalDate getDataCadastroFamilia() {
            return dataCadastroFamilia;
        }

        public void setDataCadastroFamilia(LocalDate dataCadastroFamilia) {
            this.dataCadastroFamilia = dataCadastroFamilia;
        }

        public String getFotoFamilia() {
            return fotoFamilia;
        }

        public void setFotoFamilia(String fotoFamilia) {
            this.fotoFamilia = fotoFamilia;
        }
    }

    public static class PessoaProfissao{

        private String nome;

        public PessoaProfissao(String nome){
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }
    }

    public static class PessoaDeficiencia{

        private String nome;

        public PessoaDeficiencia(String nome){
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
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

    public PessoaDeficiencia getPessoaDeficiencia() {
        return pessoaDeficiencia;
    }

    public void setPessoaDeficiencia(PessoaDeficiencia pessoaDeficiencia) {
        this.pessoaDeficiencia = pessoaDeficiencia;
    }
}

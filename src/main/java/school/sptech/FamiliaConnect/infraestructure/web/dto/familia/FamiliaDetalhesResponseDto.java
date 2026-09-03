package school.sptech.FamiliaConnect.infraestructure.web.dto.familia;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.FamiliaConnect.infraestructure.web.dto.endereco.EnderecoResponseDto;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Dados detalhados retornados da família")
public class FamiliaDetalhesResponseDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "ID da família")
    private Integer id;

    @Schema(description = "Data de cadastro da família")
    private LocalDate dataCadastro;

    @Schema(description = "Endereço de armazenamento da foto da família")
    private String fotoFamilia;

    @Schema(description = "Se a família possui integrante PNE")
    private Boolean possuiPrioridade;

    @Schema(description = "Endereço da família")
    private EnderecoResponseDto endereco;

    @Schema(description = "Responsável pela família")
    private Responsavel responsavel;

    @Schema(description = "Dependentes da família")
    private List<Dependente> dependentes;

    // Inner Classes -----------------------------------------------------------------------------------------------

    public static class Responsavel {

        private Integer id;
        private String nome;
        private String cpf;
        private String rg;
        private String telefone;
        private LocalDate dataNascimento;
        private String profissao;
        private String sexo;

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

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
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

        public String getSexo() {
            return sexo;
        }

        public void setSexo(String sexo) {
            this.sexo = sexo;
        }
    }

    public static class Dependente {

        private Integer id;
        private String nome;
        private String cpf;
        private String rg;
        private String telefone;
        private LocalDate dataNascimento;
        private String grauParentesco;
        private String profissao;
        private String sexo;

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

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
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

        public LocalDate getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
        }

        public String getGrauParentesco() {
            return grauParentesco;
        }

        public void setGrauParentesco(String grauParentesco) {
            this.grauParentesco = grauParentesco;
        }

        public String getProfissao() {
            return profissao;
        }

        public void setProfissao(String profissao) {
            this.profissao = profissao;
        }

        public String getSexo() {
            return sexo;
        }

        public void setSexo(String sexo) {
            this.sexo = sexo;
        }
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getFotoFamilia() {
        return fotoFamilia;
    }

    public void setFotoFamilia(String fotoFamilia) {
        this.fotoFamilia = fotoFamilia;
    }

    public Boolean getPossuiPrioridade() {
        return possuiPrioridade;
    }

    public void setPossuiPrioridade(Boolean possuiPrioridade) {
        this.possuiPrioridade = possuiPrioridade;
    }

    public EnderecoResponseDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoResponseDto endereco) {
        this.endereco = endereco;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<Dependente> dependentes) {
        this.dependentes = dependentes;
    }
}

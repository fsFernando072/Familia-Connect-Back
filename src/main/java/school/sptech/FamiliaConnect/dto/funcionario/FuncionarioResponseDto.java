package school.sptech.FamiliaConnect.dto.funcionario;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados do funcionário")
public class FuncionarioResponseDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "ID do funcionário")
    private Integer id;

    @Schema(description = "Nome do funcionário")
    private String nome;

    @Schema(description = "CPF do funcionário")
    private String cpf;

    @Schema(description = "Senha do funcionário")
    private String senha;

    @Schema(description = "Endereço de armazenamento da foto do funcionário")
    private String fotoFuncionario;

    @Schema(description = "Cargo do funcionário")
    private FuncionarioCargo cargo;

    // Inner Class -----------------------------------------------------------------------------------------------------

    public static class FuncionarioCargo {
        private Integer id;
        private String nome;

        public FuncionarioCargo(Integer id, String nome) {
            this.id = id;
            this.nome = nome;
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
    }

    // Construtores ----------------------------------------------------------------------------------------------------

    public FuncionarioResponseDto(Integer id, String nome, String cpf, String senha, String fotoFuncionario, FuncionarioCargo cargo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.fotoFuncionario = fotoFuncionario;
        this.cargo = cargo;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFotoFuncionario() {
        return fotoFuncionario;
    }

    public void setFotoFuncionario(String fotoFuncionario) {
        this.fotoFuncionario = fotoFuncionario;
    }

    public FuncionarioCargo getCargo() {
        return cargo;
    }

    public void setCargo(FuncionarioCargo cargo) {
        this.cargo = cargo;
    }
}

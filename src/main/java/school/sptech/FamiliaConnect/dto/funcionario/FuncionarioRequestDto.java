package school.sptech.FamiliaConnect.dto.funcionario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FuncionarioRequestDto {
    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String senha;

    private String fotoFuncionario;

    private Integer cargoId;

    public FuncionarioRequestDto() {
    }

    public FuncionarioRequestDto(String nome, String cpf, String senha, String fotoFuncionario, Integer cargoId) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.fotoFuncionario = fotoFuncionario;
        this.cargoId = cargoId;
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

    public Integer getCargoId() {
        return cargoId;
    }

    public void setCargoId(Integer cargoId) {
        this.cargoId = cargoId;
    }
}

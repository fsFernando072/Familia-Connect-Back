package school.sptech.FamiliaConnect.infraestructure.web.dto.funcionario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class FuncionarioRequestDto {

    @Schema(description = "Nome do funcionário")
    @NotBlank(message = "Nome do funcionário pe obrigatório")
    private String nome;

    @Schema(description = "CPF do funcionário")
    @NotBlank(message = "CPF do funcionário é obrigatório")
    @CPF(message = "CPF tem que ser válido")
    private String cpf;

    @Schema(description = "Senha do funcionário")
    @NotBlank(message = "Senha do funcionário tem que ser obrigatória")
    @Size(min = 8)
    private String senha;

    @Schema(description = "ID do cargo do funcionário")
    @NotNull(message = "ID do cargo do funcionário é obrigatório")
    @Positive(message = "ID do cargo do funcionário tem que ser positivo")
    private Integer cargoId;

    public FuncionarioRequestDto() {
    }

    public FuncionarioRequestDto(String nome, String cpf, String senha, Integer cargoId) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
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

    public Integer getCargoId() {
        return cargoId;
    }

    public void setCargoId(Integer cargoId) {
        this.cargoId = cargoId;
    }
}

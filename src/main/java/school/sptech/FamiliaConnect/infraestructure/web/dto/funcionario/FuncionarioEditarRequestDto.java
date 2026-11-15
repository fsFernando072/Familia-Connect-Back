package school.sptech.FamiliaConnect.infraestructure.web.dto.funcionario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class FuncionarioEditarRequestDto {


    @Schema(description = "Nome do funcionário")
    @NotBlank(message = "Nome do funcionário pe obrigatório")
    private String nome;

    @Schema(description = "CPF do funcionário")
    @NotBlank(message = "CPF do funcionário é obrigatório")
    @CPF(message = "CPF tem que ser válido")
    private String cpf;
    @Schema(description = "E-mail do funcionário")
    @NotBlank(message = "E-mail do funcionário é obrigatório")
    @Email(message = "E-mail inválido")
    @Size(max = 254, message = "O e-mail deve ter no máximo 254 caracteres")
    private String email;

    @Schema(description = "Senha do funcionário")
    @Size(min = 8)
    private String senha;

    @Schema(description = "ID do cargo do funcionário")
    @NotNull(message = "ID do cargo do funcionário é obrigatório")
    @Positive(message = "ID do cargo do funcionário tem que ser positivo")
    private Integer cargoId;

    public FuncionarioEditarRequestDto() {
    }

    public FuncionarioEditarRequestDto(String nome, String cpf, String email, String senha, Integer cargoId) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
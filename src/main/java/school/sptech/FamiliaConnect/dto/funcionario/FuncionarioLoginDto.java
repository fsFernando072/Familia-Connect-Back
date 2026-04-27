package school.sptech.FamiliaConnect.dto.funcionario;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public class FuncionarioLoginDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @NotBlank(message = "Senha do funcionário é obrigatória")
    private String senha;

    @NotBlank(message = "CPF do funcionário é obrigatório")
    @CPF(message = "CPF do funcionário deve ser válido")
    private String cpf;

    // Getters e Setters -----------------------------------------------------------------------------------------------


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

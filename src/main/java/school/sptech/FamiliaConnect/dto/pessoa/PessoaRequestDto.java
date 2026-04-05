package school.sptech.FamiliaConnect.dto.pessoa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class PessoaRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @NotBlank
    @Size(min = 2, max = 150)
    private String nome;

    @NotBlank
    @Size(min = 7, max = 9)
    private String rg;

    @CPF
    private String cpf;

    @NotBlank
    @Size(min = 11, max = 11)
    private String telefone;

    @NotNull
    private Boolean isTrabalhando;

    @NotNull
    private Boolean possuiFilhos;

    @NotNull
    private Boolean isResponsavel;
}

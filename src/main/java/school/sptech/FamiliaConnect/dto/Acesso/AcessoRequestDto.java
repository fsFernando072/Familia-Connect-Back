package school.sptech.FamiliaConnect.dto.Acesso;

import jakarta.validation.constraints.NotBlank;

public class AcessoRequestDto {
    @NotBlank
    private String nomeTela;

    public AcessoRequestDto() {}

    public AcessoRequestDto(String nomeTela) {
        this.nomeTela = nomeTela;
    }

    public String getNomeTela() {
        return nomeTela;
    }

    public void setNomeTela(String nomeTela) {
        this.nomeTela = nomeTela;
    }
}

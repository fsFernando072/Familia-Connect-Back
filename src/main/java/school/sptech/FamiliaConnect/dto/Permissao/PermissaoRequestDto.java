package school.sptech.FamiliaConnect.dto.Permissao;

import jakarta.validation.constraints.NotBlank;

public class PermissaoRequestDto {

    @NotBlank
    private String nome;

    public PermissaoRequestDto() {}

    public PermissaoRequestDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

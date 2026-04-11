package school.sptech.FamiliaConnect.dto.Estado;

import jakarta.validation.constraints.NotBlank;

public class EstadoRequestDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String sigla;

    public EstadoRequestDto() {}

    public EstadoRequestDto(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}

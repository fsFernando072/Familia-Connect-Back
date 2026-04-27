package school.sptech.FamiliaConnect.dto.Estado;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class EstadoRequestDto {

    @Schema(description = "Nome do estado")
    @NotBlank(message = "Nome do estado tem que ser obrigatório")
    private String nome;

    @Schema(description = "Sigla do estado")
    @NotBlank(message = "Sigla do estado tem que ser obrigatório")
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

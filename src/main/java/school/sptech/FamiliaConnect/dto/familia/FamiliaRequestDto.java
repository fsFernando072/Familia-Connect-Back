package school.sptech.FamiliaConnect.dto.familia;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class FamiliaRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "Data de cadastro da família")
    private LocalDate dataCadastro;

    @Schema(description = "Endereço de armazenamento da foto da família")
    private String fotoFamilia;

    @Schema(description = "ID do endereço da família")
    @NotNull(message = "ID do endereço é obrigatório")
    @Positive(message = "ID do endereço tem que ser positivo")
    private Integer enderecoId;

    @Schema(description = "Se a família possui integrante PNE")
    @NotBlank(message = "PNE é obrigatório")
    private Boolean possuiPrioridade;

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getFotoFamilia() {
        return fotoFamilia;
    }

    public void setFotoFamilia(String fotoFamilia) {
        this.fotoFamilia = fotoFamilia;
    }

    public Integer getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Integer enderecoId) {
        this.enderecoId = enderecoId;
    }

    public Boolean getPossuiPrioridade() {
        return possuiPrioridade;
    }

    public void setPossuiPrioridade(Boolean possuiPrioridade) {
        this.possuiPrioridade = possuiPrioridade;
    }
}

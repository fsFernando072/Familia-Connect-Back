package school.sptech.FamiliaConnect.dto.familia;

import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class FamiliaRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private LocalDate dataCadastro;
    private String fotoFamilia;
    private Integer enderecoId;

    // Construtores ----------------------------------------------------------------------------------------------------

    public FamiliaRequestDto() {
    }

    public FamiliaRequestDto(LocalDate dataCadastro, String fotoFamilia, Integer enderecoId) {
        this.dataCadastro = dataCadastro;
        this.fotoFamilia = fotoFamilia;
        this.enderecoId = enderecoId;
    }

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
}

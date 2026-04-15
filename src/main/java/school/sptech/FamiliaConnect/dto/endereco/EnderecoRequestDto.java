package school.sptech.FamiliaConnect.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class EnderecoRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;

    @NotBlank
    private String bairro;

    @NotBlank
    private String logradouro;

    @NotBlank
    @Positive
    private Integer numero;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    private Integer estadoId;

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}

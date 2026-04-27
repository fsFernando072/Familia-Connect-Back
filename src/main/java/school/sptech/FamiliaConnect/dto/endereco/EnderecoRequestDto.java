package school.sptech.FamiliaConnect.dto.endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class EnderecoRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "CEP do endereço")
    @NotBlank(message = "CEP do endereço é obrigatório")
    @Size(min = 8, max = 8, message = "Tem que ter obrigatoriamente 8 caracteres")
    private String cep;

    @Schema(description = "Nome do bairro do endereço")
    @NotBlank(message = "Nome do bairro é obrigatório")
    private String bairro;

    @Schema(description = "Nome do logradouro do endereço")
    @NotBlank(message = "Nome do logradouro é obrigatório")
    private String logradouro;

    @Schema(description = "Número do endereço")
    @NotBlank(message = "Número do endereço é obrigatório")
    @Positive(message = "Númeto do endereço tem que ser positivo")
    private Integer numero;

    @Schema(description = "Complemento do endereço")
    @NotBlank(message = "Nome do complemento é obrigatório")
    private String complemento;

    @Schema(description = "Nome da cidade do endereço")
    @NotBlank(message = "Nome da cidade é obrigatório")
    private String cidade;

    @Schema(description = "ID do estado do endereço")
    @NotNull(message = "ID do estado é obrigatório")
    @Positive(message = "ID do estado tem que ser positivo")
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

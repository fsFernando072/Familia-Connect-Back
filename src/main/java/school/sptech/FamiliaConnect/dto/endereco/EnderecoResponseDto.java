package school.sptech.FamiliaConnect.dto.endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.FamiliaConnect.model.Estado;

@Schema(description = "Dados retornados do endereço")
public class EnderecoResponseDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "ID do endereço")
    private Integer id;

    @Schema(description = "CEP do endereço")
    private String cep;

    @Schema(description = "Nome do bairro do endereço")
    private String bairro;

    @Schema(description = "Nome do logradouro do endereço")
    private String logradouro;

    @Schema(description = "Número do endereço")
    private Integer numero;

    @Schema(description = "Complemento do endereço")
    private String complemento;

    @Schema(description = "Nome da cidade do endereço")
    private String cidade;

    @Schema(description = "Estado do endereço")
    private EnderecoEstado enderecoEstado;

    // Inner Class -----------------------------------------------------------------------------------------------------

    public static class EnderecoEstado{

        private Integer id;
        private String nome;
        private String sigla;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public EnderecoEstado getEnderecoEstado() {
        return enderecoEstado;
    }

    public void setEnderecoEstado(EnderecoEstado enderecoEstado) {
        this.enderecoEstado = enderecoEstado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}

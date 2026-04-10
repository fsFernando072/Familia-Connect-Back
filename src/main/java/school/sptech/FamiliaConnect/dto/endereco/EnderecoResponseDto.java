package school.sptech.FamiliaConnect.dto.endereco;

import school.sptech.FamiliaConnect.model.Estado;

public class EnderecoResponseDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private Integer id;
    private String cep;
    private String bairro;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private EnderecoEstado enderecoEstado;

    // Construtores ----------------------------------------------------------------------------------------------------

    public EnderecoResponseDto() {
    }

    public EnderecoResponseDto(Integer id, String cep, String bairro, String logradouro, Integer numero, String complemento, EnderecoEstado enderecoEstado) {
        this.id = id;
        this.cep = cep;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.enderecoEstado = enderecoEstado;
    }

    // Inner Class -----------------------------------------------------------------------------------------------------

    public static class EnderecoEstado{

        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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
}

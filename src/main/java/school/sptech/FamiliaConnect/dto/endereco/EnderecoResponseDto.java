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
    private String cidade;
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

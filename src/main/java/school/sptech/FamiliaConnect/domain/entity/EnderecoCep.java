package school.sptech.FamiliaConnect.domain.entity;

/**
 * Representa o endereço retornado por uma consulta de CEP em um provedor externo.
 * <p>
 * Diferente de {@link Endereco}, não é uma entidade persistida (sem @Entity/@Id):
 * é apenas o resultado de uma busca, usado para pré-preencher o formulário de
 * endereço no front antes de o usuário salvar o cadastro.
 */
public class EnderecoCep {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}

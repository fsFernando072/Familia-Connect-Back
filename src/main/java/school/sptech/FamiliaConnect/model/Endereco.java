package school.sptech.FamiliaConnect.model;

import jakarta.persistence.*;

@Entity
public class Endereco {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    private Integer id;
    private String cep;
    private String bairro;
    private String logradouro;
    private Integer numero;
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    // Construtores ----------------------------------------------------------------------------------------------------

    public Endereco() {
    }

    public Endereco(String bairro, String complemento, String cpf, Estado estado, Integer id, String logradouro, Integer numero) {
        this.bairro = bairro;
        this.complemento = complemento;
        this.cep = cpf;
        this.estado = estado;
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
    }

    public Endereco(String bairro, String complemento, String cpf, Estado estado, String logradouro, Integer numero) {
        this.bairro = bairro;
        this.complemento = complemento;
        this.cep = cpf;
        this.estado = estado;
        this.logradouro = logradouro;
        this.numero = numero;
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}

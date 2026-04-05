package school.sptech.FamiliaConnect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Endereco {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    private Integer id;
    private String cpf;
    private String bairro;
    private String logradouro;
    private Integer numero;
    private String estado;
    private String complemento;

    // Construtores ----------------------------------------------------------------------------------------------------

    public Endereco() {
    }

    public Endereco(String bairro, String complemento, String cpf, String estado, Integer id, String logradouro, Integer numero) {
        this.bairro = bairro;
        this.complemento = complemento;
        this.cpf = cpf;
        this.estado = estado;
        this.id = id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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

package school.sptech.FamiliaConnect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Familia {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    private Integer id;
    private LocalDate dataCadastro;
    private String fotoFamilia;
    private Endereco endereco;

    // Construtores ----------------------------------------------------------------------------------------------------

    public Familia() {
    }

    public Familia(LocalDate dataCadastro, Endereco endereco, String fotoFamilia, Integer id) {
        this.dataCadastro = dataCadastro;
        this.endereco = endereco;
        this.fotoFamilia = fotoFamilia;
        this.id = id;
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getFotoFamilia() {
        return fotoFamilia;
    }

    public void setFotoFamilia(String fotoFamilia) {
        this.fotoFamilia = fotoFamilia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

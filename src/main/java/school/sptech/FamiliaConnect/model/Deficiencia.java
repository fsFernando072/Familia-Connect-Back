package school.sptech.FamiliaConnect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Deficiencia {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    private Integer id;
    private String nome;

    // Construtores ----------------------------------------------------------------------------------------------------

    public Deficiencia() {
    }

    public Deficiencia(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

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
}

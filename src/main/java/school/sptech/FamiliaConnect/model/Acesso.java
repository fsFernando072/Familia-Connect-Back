package school.sptech.FamiliaConnect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Acesso {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    private Integer id;
    private String nomeTela;

    // Construtores ----------------------------------------------------------------------------------------------------

    public Acesso() {
    }

    public Acesso(Integer id, String nomeTela) {
        this.id = id;
        this.nomeTela = nomeTela;
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeTela() {
        return nomeTela;
    }

    public void setNomeTela(String nomeTela) {
        this.nomeTela = nomeTela;
    }
}

package school.sptech.FamiliaConnect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cargo {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    private Integer idCargo;
    private String nome;

    // Construtores ----------------------------------------------------------------------------------------------------

    public Cargo() {
    }

    public Cargo(Integer idCargo, String nome) {
        this.idCargo = idCargo;
        this.nome = nome;
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

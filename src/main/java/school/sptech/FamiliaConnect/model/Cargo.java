package school.sptech.FamiliaConnect.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cargo")
public class Cargo {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCargo;

    @Column(nullable = false, length = 45)
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

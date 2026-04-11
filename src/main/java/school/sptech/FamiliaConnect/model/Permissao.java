package school.sptech.FamiliaConnect.model;

import jakarta.persistence.*;

@Entity
@Table(name = "permissao")
public class Permissao {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String nome;

    // Construtores ----------------------------------------------------------------------------------------------------

    public Permissao() {
    }

    public Permissao(Integer id, String nome) {
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

package school.sptech.FamiliaConnect.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cargo_has_acesso")
public class CargoHasAcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "acesso_id")
    private Acesso acesso;

    @ManyToOne
    @JoinColumn(name = "permissao_id")
    private Permissao permissao;

    public CargoHasAcesso(Cargo cargo, Acesso acesso, Permissao permissao) {
        this.id = id;
        this.cargo = cargo;
        this.acesso = acesso;
        this.permissao = permissao;
    }

    public CargoHasAcesso() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }
}

package school.sptech.FamiliaConnect.domain.entity;

import jakarta.persistence.*;
import school.sptech.FamiliaConnect.domain.enums.NivelAcessoEnum;
import school.sptech.FamiliaConnect.domain.enums.PaginaEnum;

/**
 * Uma linha por página marcada no cargo: "o cargo X acessa a página Y com o nível Z".
 */
@Entity
@Table(
        name = "cargo_permissao",
        uniqueConstraints = @UniqueConstraint(name = "uk_cargo_pagina", columnNames = {"cargo_id", "pagina"})
)
public class CargoPermissao {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaginaEnum pagina;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private NivelAcessoEnum nivel;

    // Construtores ----------------------------------------------------------------------------------------------------

    public CargoPermissao() {
    }

    public CargoPermissao(Cargo cargo, PaginaEnum pagina, NivelAcessoEnum nivel) {
        this.cargo = cargo;
        this.pagina = pagina;
        this.nivel = nivel;
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

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

    public PaginaEnum getPagina() {
        return pagina;
    }

    public void setPagina(PaginaEnum pagina) {
        this.pagina = pagina;
    }

    public NivelAcessoEnum getNivel() {
        return nivel;
    }

    public void setNivel(NivelAcessoEnum nivel) {
        this.nivel = nivel;
    }
}

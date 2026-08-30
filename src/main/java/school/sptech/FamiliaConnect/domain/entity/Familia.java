package school.sptech.FamiliaConnect.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Familia {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dataCadastro;

    // Referência de verdade ao arquivo da foto (antes era só uma String com o caminho/URL).
    @ManyToOne
    private Arquivo foto;

    private Boolean possuiPrioridade;

    @OneToOne
    private Endereco endereco;

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

    public Arquivo getFoto() {
        return foto;
    }

    public void setFoto(Arquivo foto) {
        this.foto = foto;
    }

    // Helper para expor a foto como URL nos DTOs de resposta, sem espalhar essa lógica pelos mappers.
    public String getFotoUrl() {
        return foto != null ? "/arquivos/" + foto.getId() + "/visualizar" : null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getPossuiPrioridade() {
        return possuiPrioridade;
    }

    public void setPossuiPrioridade(Boolean possuiPrioridade) {
        this.possuiPrioridade = possuiPrioridade;
    }
}

package school.sptech.FamiliaConnect.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "auditoria")
public class Auditoria {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo_log", length = 45)
    private String tipoLog;

    @Column(name = "dado_antigo", length = 45)
    private String dadoAntigo;

    @Column(name = "dado_novo", length = 45)
    private String dadoNovo;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getDadoAntigo() {
        return dadoAntigo;
    }

    public void setDadoAntigo(String dadoAntigo) {
        this.dadoAntigo = dadoAntigo;
    }

    public String getDadoNovo() {
        return dadoNovo;
    }

    public void setDadoNovo(String dadoNovo) {
        this.dadoNovo = dadoNovo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoLog() {
        return tipoLog;
    }

    public void setTipoLog(String tipoLog) {
        this.tipoLog = tipoLog;
    }
}

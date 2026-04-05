package school.sptech.FamiliaConnect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Auditoria {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Id
    private Integer id;
    private String tipoLog;
    private String dadoAntigo;
    private String dadoNovo;
    private LocalDate createdAt;
    private Funcionario funcionario;

    // Construtores ----------------------------------------------------------------------------------------------------

    public Auditoria() {
    }

    public Auditoria(LocalDate createdAt, String dadoAntigo, String dadoNovo, Funcionario funcionario, Integer id, String tipoLog) {
        this.createdAt = createdAt;
        this.dadoAntigo = dadoAntigo;
        this.dadoNovo = dadoNovo;
        this.funcionario = funcionario;
        this.id = id;
        this.tipoLog = tipoLog;
    }

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

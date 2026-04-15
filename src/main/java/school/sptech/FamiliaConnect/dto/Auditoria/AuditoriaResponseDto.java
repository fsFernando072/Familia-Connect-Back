package school.sptech.FamiliaConnect.dto.Auditoria;

import java.time.LocalDate;

public class AuditoriaResponseDto {

    private Integer id;
    private String tipoLog;
    private String dadoAntigo;
    private String dadoNovo;
    private LocalDate createdAt;
    private Integer funcionarioId;
    private String funcionarioNome;

    public AuditoriaResponseDto() {}

    public AuditoriaResponseDto(Integer id, String tipoLog, String dadoAntigo, String dadoNovo,
                                LocalDate createdAt, Integer funcionarioId, String funcionarioNome) {
        this.id = id;
        this.tipoLog = tipoLog;
        this.dadoAntigo = dadoAntigo;
        this.dadoNovo = dadoNovo;
        this.createdAt = createdAt;
        this.funcionarioId = funcionarioId;
        this.funcionarioNome = funcionarioNome;
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

    public String getDadoAntigo() {
        return dadoAntigo;
    }

    public void setDadoAntigo(String acaoAntigo) {
        this.dadoAntigo = acaoAntigo;
    }

    public String getDadoNovo() {
        return dadoNovo;
    }

    public void setDadoNovo(String acaoNovo) {
        this.dadoNovo = acaoNovo;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Integer funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getFuncionarioNome() {
        return funcionarioNome;
    }

    public void setFuncionarioNome(String funcionarioNome) {
        this.funcionarioNome = funcionarioNome;
    }

}

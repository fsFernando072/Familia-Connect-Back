package school.sptech.FamiliaConnect.dto.Auditoria;

import java.time.LocalDate;

public class AuditoriaResponseDto {

    private Integer id;
    private String tipoLog;
    private String acao;
    private String acaoAntigo;
    private String acaoNovo;
    private LocalDate createdAt;
    private Integer funcionarioId;
    private String funcionarioNome;

    public AuditoriaResponseDto(Integer id, String tipoLog, String dadoAntigo, String dadoNovo, LocalDate createdAt, Integer integer, String nome) {}

    public AuditoriaResponseDto(Integer id, String tipoLog, String acao, String acaoAntigo, String acaoNovo,
                                LocalDate createdAt, Integer funcionarioId, String funcionarioNome) {
        this.id = id;
        this.tipoLog = tipoLog;
        this.acao = acao;
        this.acaoAntigo = acaoAntigo;
        this.acaoNovo = acaoNovo;
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

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getAcaoAntigo() {
        return acaoAntigo;
    }

    public void setAcaoAntigo(String acaoAntigo) {
        this.acaoAntigo = acaoAntigo;
    }

    public String getAcaoNovo() {
        return acaoNovo;
    }

    public void setAcaoNovo(String acaoNovo) {
        this.acaoNovo = acaoNovo;
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

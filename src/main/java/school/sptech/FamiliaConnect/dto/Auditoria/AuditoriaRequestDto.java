package school.sptech.FamiliaConnect.dto.Auditoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AuditoriaRequestDto {

    @NotBlank
    private String tipoLog;

    @NotBlank
    private String acao;

    private String acaoAntigo;

    private String acaoNovo;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private Integer funcionarioId;

    public AuditoriaRequestDto() {}

    public AuditoriaRequestDto(String tipoLog, String acao, String acaoAntigo, String acaoNovo, LocalDate createdAt, Integer funcionarioId) {
        this.tipoLog = tipoLog;
        this.acao = acao;
        this.acaoAntigo = acaoAntigo;
        this.acaoNovo = acaoNovo;
        this.createdAt = createdAt;
        this.funcionarioId = funcionarioId;
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

}

package school.sptech.FamiliaConnect.dto.Auditoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AuditoriaRequestDto {

    @NotBlank
    private String tipoLog;

    private String dadoAntigo;

    private String dadoNovo;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private Integer funcionarioId;

    public AuditoriaRequestDto() {}

    public AuditoriaRequestDto(String tipoLog, String dadoAntigo, String dadoNovo, LocalDate createdAt, Integer funcionarioId) {
        this.tipoLog = tipoLog;
        this.dadoAntigo = dadoAntigo;
        this.dadoNovo = dadoNovo;
        this.createdAt = createdAt;
        this.funcionarioId = funcionarioId;
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

    public void setDadoAntigo(String dadoAntigo) {
        this.dadoAntigo = dadoAntigo;
    }

    public String getDadoNovo() {
        return dadoNovo;
    }

    public void setDadoNovo(String dadoNovo) {
        this.dadoNovo = dadoNovo;
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

package school.sptech.FamiliaConnect.domain.entity.carteirinha;

import java.util.UUID;


public class ProcessamentoCarteirinha {
    private String status;
    private UUID correlationId;

    public ProcessamentoCarteirinha() {
    }

    public ProcessamentoCarteirinha(String status, UUID correlationId) {
        this.status = status;
        this.correlationId = correlationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(UUID correlationId) {
        this.correlationId = correlationId;
    }
}

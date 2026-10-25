package school.sptech.FamiliaConnect.domain.entity.carteirinha;

public class StatusCarteirinha {

    private String status;
    private String message;
    private String nomeResponsavel;

    public StatusCarteirinha() {
    }

    public StatusCarteirinha(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public StatusCarteirinha(String status, String message, String nomeResponsavel) {
        this.status = status;
        this.message = message;
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }
}

package school.sptech.FamiliaConnect.domain.entity.carteirinha;

public class StatusCarteirinha {

    private String status;
    private String message;

    public StatusCarteirinha() {
    }

    public StatusCarteirinha(String status, String message) {
        this.status = status;
        this.message = message;
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

    public void setMessage(String message) {
        this.message = message;
    }
}

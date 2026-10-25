package school.sptech.FamiliaConnect.infraestructure.web.dto.carteirinha;

public class StatusCarteirinhaResponseDto {

    private String status;
    private String message;
    private String nomeResponsavel;

    public StatusCarteirinhaResponseDto() {
    }

    public StatusCarteirinhaResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public StatusCarteirinhaResponseDto(String status, String message, String nomeResponsavel) {
        this.status = status;
        this.message = message;
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
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

    public void setMessage(String message) {
        this.message = message;
    }
}

package school.sptech.FamiliaConnect.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DadosDaFamiliaAusenteException extends RuntimeException {
    public DadosDaFamiliaAusenteException(String message) {
        super(message);
    }
}

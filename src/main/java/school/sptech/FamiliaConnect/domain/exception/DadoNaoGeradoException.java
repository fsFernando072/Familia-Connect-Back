package school.sptech.FamiliaConnect.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DadoNaoGeradoException extends RuntimeException {
    public DadoNaoGeradoException(String message) {
        super(message);
    }
}

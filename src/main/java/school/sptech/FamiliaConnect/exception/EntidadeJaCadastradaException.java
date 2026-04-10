package school.sptech.FamiliaConnect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeJaCadastradaException extends RuntimeException {
    public EntidadeJaCadastradaException(String message) {
        super(message);
    }
}

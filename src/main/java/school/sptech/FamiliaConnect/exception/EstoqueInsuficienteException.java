package school.sptech.FamiliaConnect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EstoqueInsuficienteException extends RuntimeException {

    public EstoqueInsuficienteException(String message) {
        super(message);
    }
}

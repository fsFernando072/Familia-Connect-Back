package school.sptech.FamiliaConnect.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class TipoDeArquivoIncompativelException extends RuntimeException {
    public TipoDeArquivoIncompativelException(String message) {
        super(message);
    }
}

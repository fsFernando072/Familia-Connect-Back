package school.sptech.FamiliaConnect.infraestructure.web.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import school.sptech.FamiliaConnect.domain.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.domain.exception.EstoqueInsuficienteException;

import java.util.Map;

// Devolve o motivo do erro no corpo ("message") para o front poder exibi-lo.
// Os status continuam os mesmos definidos nas exceções (404 e 409).
@RestControllerAdvice
public class ExceptionHandler {

    // Nome completo da anotação: esta classe também se chama ExceptionHandler.
    @org.springframework.web.bind.annotation.ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Map<String, Object>> naoEncontrada(EntidadeNaoEncontradaException e) {
        return resposta(HttpStatus.NOT_FOUND, e);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({EntidadeJaCadastradaException.class, EstoqueInsuficienteException.class})
    public ResponseEntity<Map<String, Object>> conflito(RuntimeException e) {
        return resposta(HttpStatus.CONFLICT, e);
    }

    private ResponseEntity<Map<String, Object>> resposta(HttpStatus status, Exception e) {
        String mensagem = e.getMessage() == null ? "" : e.getMessage();

        return ResponseEntity.status(status).body(Map.of(
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", mensagem
        ));
    }
}

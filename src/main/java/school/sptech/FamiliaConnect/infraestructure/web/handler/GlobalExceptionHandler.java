package school.sptech.FamiliaConnect.infraestructure.web.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import school.sptech.FamiliaConnect.domain.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.domain.exception.EstoqueInsuficienteException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> integridade(DataIntegrityViolationException e) {
        return ResponseEntity.status(409).body(Map.of("status", 409,
                "message", "Registro em uso ou duplicado. Verifique os vínculos antes de continuar."));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> argumentoInvalido(IllegalArgumentException e) {
        return ResponseEntity.status(400).body(Map.of("status", 400,
                "message", "Argumento inválido. Verifique os argumentos antes de continuar."));
    }

    // Nome completo da anotação: esta classe também se chama ExceptionHandler.
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Map<String, Object>> naoEncontrada(EntidadeNaoEncontradaException e) {
        return resposta(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler({EntidadeJaCadastradaException.class, EstoqueInsuficienteException.class})
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

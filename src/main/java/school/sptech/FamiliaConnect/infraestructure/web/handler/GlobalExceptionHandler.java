package school.sptech.FamiliaConnect.infraestructure.web.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}

package school.sptech.FamiliaConnect.infraestructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.FamiliaConnect.application.service.GrafanaMetricasService;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

@Tag(name = "Dashboard", description = "Indicadores reais consultados pelo Grafana")
@RestController
@RequestMapping("/integracoes/grafana")
public class GrafanaMetricasController {
    private final GrafanaMetricasService service;
    private final String secret;

    public GrafanaMetricasController(GrafanaMetricasService service,
            @Value("${grafana.api-token:}") String secret) {
        this.service = service;
        this.secret = secret;
    }

    @Operation(summary = "Consultar dados de um painel do dashboard")
    @GetMapping("/paineis/{id}")
    public List<Map<String, Object>> painel(@PathVariable String id,
            @RequestHeader(value = "X-Grafana-Token", required = false) String token,
            @RequestParam(required = false) Long from,
            @RequestParam(required = false) Long to,
            @RequestParam(required = false) Long familiaId) {
        byte[] esperado = secret.getBytes(StandardCharsets.UTF_8);
        byte[] recebido = (token == null ? "" : token).getBytes(StandardCharsets.UTF_8);
        if (secret.isBlank() || !MessageDigest.isEqual(esperado, recebido)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return service.consultar(id, from, to, familiaId);
    }
}

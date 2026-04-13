package school.sptech.FamiliaConnect.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.Auditoria.AuditoriaRequestDto;
import school.sptech.FamiliaConnect.dto.Auditoria.AuditoriaResponseDto;
import school.sptech.FamiliaConnect.mapper.AuditoriaMapper;
import school.sptech.FamiliaConnect.model.Auditoria;
import school.sptech.FamiliaConnect.service.AuditoriaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auditorias")
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    public AuditoriaController(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @PostMapping
    public ResponseEntity<AuditoriaResponseDto> cadastrar(@RequestBody @Valid AuditoriaRequestDto dto) {
        Optional<Auditoria> auditoriaOpt = auditoriaService.cadastrar(dto);

        if (auditoriaOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(201).body(AuditoriaMapper.toResponse(auditoriaOpt.get()));
    }

    @GetMapping
    public ResponseEntity<List<AuditoriaResponseDto>> listar() {
        List<Auditoria> auditorias = auditoriaService.listar();

        if (auditorias.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(AuditoriaMapper.toResponseList(auditorias));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditoriaResponseDto> buscarPorId(@PathVariable Integer id) {
        Optional<Auditoria> auditoriaOpt = auditoriaService.buscarPorId(id);

        if (auditoriaOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(AuditoriaMapper.toResponse(auditoriaOpt.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditoriaResponseDto> atualizar(@PathVariable Integer id,
                                                          @RequestBody @Valid AuditoriaRequestDto dto) {
        Optional<Auditoria> auditoriaOpt = auditoriaService.atualizar(id, dto);

        if (auditoriaOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(AuditoriaMapper.toResponse(auditoriaOpt.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean deletou = auditoriaService.deletar(id);

        if (!deletou) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(204).build();
    }

}

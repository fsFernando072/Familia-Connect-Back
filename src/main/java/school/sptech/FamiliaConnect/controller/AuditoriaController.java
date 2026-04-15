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

@RestController
@RequestMapping("/auditorias")
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    public AuditoriaController(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @PostMapping
    public ResponseEntity<AuditoriaResponseDto> cadastrar(@RequestBody @Valid AuditoriaRequestDto dto) {
        Auditoria auditoria = auditoriaService.cadastrar(dto);
        AuditoriaResponseDto responseDto = AuditoriaMapper.toResponse(auditoria);

        return ResponseEntity.status(201).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<AuditoriaResponseDto>> listar() {
        List<Auditoria> auditorias = auditoriaService.listar();

        if (auditorias.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(AuditoriaMapper.toResponse(auditorias));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditoriaResponseDto> buscarPorId(@PathVariable Integer id) {
        Auditoria auditoria = auditoriaService.buscarPorId(id);
        AuditoriaResponseDto responseDto = AuditoriaMapper.toResponse(auditoria);

        return ResponseEntity.status(200).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditoriaResponseDto> atualizar(@PathVariable Integer id,
                                                          @RequestBody @Valid AuditoriaRequestDto dto) {
        Auditoria auditoria = auditoriaService.atualizar(id, dto);
        AuditoriaResponseDto responseDto = AuditoriaMapper.toResponse(auditoria);

        return ResponseEntity.status(200).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        auditoriaService.deletar(id);

        return ResponseEntity.status(204).build();
    }

}

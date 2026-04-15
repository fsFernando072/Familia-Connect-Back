package school.sptech.FamiliaConnect.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.Estado.EstadoRequestDto;
import school.sptech.FamiliaConnect.dto.Estado.EstadoResponseDto;
import school.sptech.FamiliaConnect.mapper.EstadoMapper;
import school.sptech.FamiliaConnect.model.Estado;
import school.sptech.FamiliaConnect.service.EstadoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
    public class EstadoController {

        private final EstadoService estadoService;

        public EstadoController(EstadoService estadoService) {
            this.estadoService = estadoService;
        }

        @PostMapping
        public ResponseEntity<EstadoResponseDto> cadastrar(@RequestBody @Valid EstadoRequestDto dto) {
            Estado estado = estadoService.cadastrar(dto);
            return ResponseEntity.status(201).body(EstadoMapper.toResponse(estado));
        }

        @GetMapping
        public ResponseEntity<List<EstadoResponseDto>> listar() {
            List<Estado> estados = estadoService.listar();

            if (estados.isEmpty()) {
                return ResponseEntity.status(204).build();
            }

            return ResponseEntity.status(200).body(EstadoMapper.toResponse(estados));
        }

        @GetMapping("/{id}")
        public ResponseEntity<EstadoResponseDto> buscarPorId(@PathVariable Integer id) {
            Estado estado = estadoService.buscarPorId(id);
            EstadoResponseDto responseDto = EstadoMapper.toResponse(estado);

            return ResponseEntity.status(200).body(responseDto);
        }

        @PutMapping("/{id}")
        public ResponseEntity<EstadoResponseDto> atualizar(@PathVariable Integer id,
                                                           @RequestBody @Valid EstadoRequestDto dto) {
            Estado estado = estadoService.atualizar(id, dto);
            EstadoResponseDto responseDto = EstadoMapper.toResponse(estado);

            return ResponseEntity.status(200).body(responseDto);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Integer id) {
            estadoService.deletar(id);

            return ResponseEntity.status(204).build();
        }
}

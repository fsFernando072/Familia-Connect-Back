package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Estados", description = "Operações relacionadas aos estados cadastrados no sistema")
@RestController
@RequestMapping("/estados")
    public class EstadoController {

        private final EstadoService estadoService;

        public EstadoController(EstadoService estadoService) {
            this.estadoService = estadoService;
        }

        @Operation(
            summary = "Listar estados",
            description = "Retorna uma lista dos estados cadastrados no sistema"
        )
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Lista de estados retornada com sucesso"),
                @ApiResponse(responseCode = "204", description = "Lista de estados retornada vazia")
        })
        @GetMapping
        public ResponseEntity<List<EstadoResponseDto>> listar() {
            List<Estado> estados = estadoService.listar();

            if (estados.isEmpty()) {
                return ResponseEntity.status(204).build();
            }

            return ResponseEntity.status(200).body(EstadoMapper.toResponse(estados));
        }
        @Operation(
                summary = "Listar estado",
                description = "Retorna um estado pelo ID fornecido"
        )
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Estado retornado com sucesso pelo ID"),
                @ApiResponse(responseCode = "404", description = "Estado não encontrado pelo ID")
        })
        @GetMapping("/{id}")
        public ResponseEntity<EstadoResponseDto> buscarPorId(@PathVariable Integer id) {
            Estado estado = estadoService.buscarPorId(id);
            EstadoResponseDto responseDto = EstadoMapper.toResponse(estado);

            return ResponseEntity.status(200).body(responseDto);
        }
}

package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.entrega.EntregaRequestDto;
import school.sptech.FamiliaConnect.dto.entrega.EntregaResponseDto;
import school.sptech.FamiliaConnect.mapper.EntregaMapper;
import school.sptech.FamiliaConnect.model.Entrega;
import school.sptech.FamiliaConnect.service.EntregaService;

import java.util.List;

@Tag(name = "Entregas", description = "Operações relacionadas às entregas realizadas às famílias")
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final EntregaService entregaService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Listar entregas",
            description = "Retorna uma lista com as entregas realizadas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de entregas retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de entregas retornada vazia")
    })
    @GetMapping
    public ResponseEntity<List<EntregaResponseDto>> listarEntregas(){

        List<Entrega> entregas = entregaService.listar();

        if(entregas.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(EntregaMapper.toResponse(entregas));

    }

    @Operation(
            summary = "Listar entrega",
            description = "Retorna uma entrega realizada pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Entrega não encontrada pelo ID"),
            @ApiResponse(responseCode = "200", description = "Entrega retornada com sucesso pelo ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntregaResponseDto> listarPorId(@PathVariable Integer id){

        Entrega entrega = entregaService.listarPorId(id);

        return ResponseEntity.status(200).body(EntregaMapper.toResponse(entrega));

    }

    @Operation(
            summary = "Cadastrar entrega",
            description = "Cadastra uma entrega pelos dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Entrega cadastrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado (Pessoa, Funcionário ou Produto)")
    })
    @PostMapping
    public ResponseEntity<EntregaResponseDto> cadastrarEntrega(@RequestBody @Valid EntregaRequestDto requestDto){

        Entrega entrega = entregaService.salvar(requestDto);

        return ResponseEntity.status(201).body(EntregaMapper.toResponse(entrega));

    }

}

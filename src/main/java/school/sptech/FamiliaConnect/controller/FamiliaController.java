package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.familia.FamiliaRequestDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaResponseDto;
import school.sptech.FamiliaConnect.mapper.FamiliaMapper;
import school.sptech.FamiliaConnect.model.Familia;
import school.sptech.FamiliaConnect.service.FamiliaService;

import java.util.List;

@Tag(name = "Famílias", description = "Operações relacionadas às famílias")
@RestController
@RequestMapping("/familias")
public class FamiliaController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final FamiliaService familiaService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public FamiliaController(FamiliaService familiaService) {
        this.familiaService = familiaService;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Cadastrar família",
            description = "Cadastra uma família pelos dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Família cadastrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado pelo ID")
    })
    @PostMapping
    public ResponseEntity<FamiliaResponseDto> cadastrarFamilia(@RequestBody @Valid FamiliaRequestDto familiaRequestDto){

        Familia familiaCadastrada = familiaService.salvar(familiaRequestDto);

        FamiliaResponseDto responseDto = FamiliaMapper.toResponse(familiaCadastrada);

        return ResponseEntity.status(201).body(responseDto);

    }

    @Operation(
            summary = "Listar famílias",
            description = "Retorna uma lista com todas as famílias cadastradas no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de famílias retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de famílias retornada vazia")
    })
    @GetMapping
    public ResponseEntity<List<FamiliaResponseDto>> listarFamilias(){

        List<Familia> familias = familiaService.listar();

        if (familias.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(FamiliaMapper.toResponse(familias));

    }

    @Operation(
            summary = "Atualizar família",
            description = "Atualiza uma família com os dados fornecidos pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Família atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Família não encontrada pelo ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FamiliaResponseDto> atualizarFamilia(@PathVariable Integer idFamilia) {

        Familia familia = familiaService.listarPorId(idFamilia);

        FamiliaResponseDto responseDto = FamiliaMapper.toResponse(familia);

        return ResponseEntity.status(200).body(responseDto);

    }
}

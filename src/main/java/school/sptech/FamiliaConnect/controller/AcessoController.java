package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.Acesso.AcessoRequestDto;
import school.sptech.FamiliaConnect.dto.Acesso.AcessoResponseDto;
import school.sptech.FamiliaConnect.mapper.AcessoMapper;
import school.sptech.FamiliaConnect.model.Acesso;
import school.sptech.FamiliaConnect.service.AcessoService;

import java.util.List;
import java.util.Optional;

@Tag(name = "Acessos", description = "Operações relacionadas aos acessos às telas do sistema")
@RestController
@RequestMapping("/acessos")
public class AcessoController {

    private final AcessoService acessoService;

    public AcessoController(AcessoService acessoService) {
        this.acessoService = acessoService;
    }

    @Operation(
            summary = "Cadastrar um acesso",
            description = "Cadastra um nível de acesso com os dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Acesso cadastrado com sucesso")
    })
    @PostMapping
    public ResponseEntity<AcessoResponseDto> cadastrar(@RequestBody @Valid AcessoRequestDto dto) {
        Acesso acesso = acessoService.cadastrar(dto);
        AcessoResponseDto responseDto = AcessoMapper.toResponse(acesso);

        return ResponseEntity.status(201).body(responseDto);
    }

    @Operation(
            summary = "Listar acessos",
            description = "Retorna uma lista todos os acessos cadastrados no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de acessos retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de acessos retornada vazia")
    })
    @GetMapping
    public ResponseEntity<List<AcessoResponseDto>> listar() {
        List<Acesso> acessos = acessoService.listar();

        if (acessos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(AcessoMapper.toResponse(acessos));
    }

    @Operation(
            summary = "Listar acesso pelo ID",
            description = "Retorna o acesso pelo id fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acesso retornado com sucesso pelo ID"),
            @ApiResponse(responseCode = "404", description = "Acesso não encontrado pelo ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AcessoResponseDto> buscarPorId(@PathVariable Integer id) {
        Acesso acesso = acessoService.buscarPorId(id);
        AcessoResponseDto responseDto = AcessoMapper.toResponse(acesso);

        return ResponseEntity.status(200).body(responseDto);
    }

    @Operation(
            summary = "Atualizar acesso",
            description = "Atualiza um acesso pelos dados enviados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acesso atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Acesso não encontrado pelo ID")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AcessoResponseDto> atualizar(@PathVariable Integer id,
                                                       @RequestBody @Valid AcessoRequestDto dto) {
        Acesso acesso = acessoService.atualizar(id, dto);
        AcessoResponseDto responseDto = AcessoMapper.toResponse(acesso);

        return ResponseEntity.status(200).body(responseDto);
    }

    @Operation(
            summary = "Deletar acesso",
            description = "Deleta um acesso pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acesso removido com sucesso pelo ID"),
            @ApiResponse(responseCode = "404", description = "Acesso não encontrado pelo ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        acessoService.deletar(id);

        return ResponseEntity.status(204).build();
    }
}

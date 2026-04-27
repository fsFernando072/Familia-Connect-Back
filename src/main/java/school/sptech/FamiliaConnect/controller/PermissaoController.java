package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.Permissao.PermissaoRequestDto;
import school.sptech.FamiliaConnect.dto.Permissao.PermissaoResponseDto;
import school.sptech.FamiliaConnect.mapper.PermissaoMapper;
import school.sptech.FamiliaConnect.model.Permissao;
import school.sptech.FamiliaConnect.service.PermissaoService;

import java.util.List;

@Tag(name = "Permissões", description = "Operações relacionadas às permissões das telas do sistema")
@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    private final PermissaoService permissaoService;

    public PermissaoController(PermissaoService permissaoService) {
        this.permissaoService = permissaoService;
    }

    @Operation(
            summary = "Cadastrar permissão",
            description = "Cadastra uma permissão pelos dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permissão cadastrada com sucesso")
    })
    @PostMapping
    public ResponseEntity<PermissaoResponseDto> cadastrar(@RequestBody @Valid PermissaoRequestDto dto) {
        Permissao permissao = permissaoService.cadastrar(dto);
        PermissaoResponseDto responseDto = PermissaoMapper.toResponse(permissao);

        return ResponseEntity.status(201).body(responseDto);
    }

    @Operation(
            summary = "Listar permissões",
            description = "Lista todas as permissões cadastradas no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista de permissões retornada vazia"),
            @ApiResponse(responseCode = "200", description = "Lista de permissões retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<PermissaoResponseDto>> listar() {
        List<Permissao> permissoes = permissaoService.listar();

        if (permissoes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(PermissaoMapper.toResponse(permissoes));
    }

    @Operation(
            summary = "Listar permissão",
            description = "Lista uma permissão pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permissão retornada com sucesso pelo ID"),
            @ApiResponse(responseCode = "404", description = "Permissão não encontrada pelo ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PermissaoResponseDto> buscarPorId(@PathVariable Integer id) {
        Permissao permissao = permissaoService.buscarPorId(id);
        PermissaoResponseDto responseDto = PermissaoMapper.toResponse(permissao);

        return ResponseEntity.status(200).body(responseDto);
    }

    @Operation(
            summary = "Atualizar permissão",
            description = "Atualiza uma permissão com os dados fornecidos pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permissão atualizada com sucesso pelo ID"),
            @ApiResponse(responseCode = "404", description = "Permissão não encontrada pelo ID")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PermissaoResponseDto> atualizar(@PathVariable Integer id,
                                                          @RequestBody @Valid PermissaoRequestDto dto) {
        Permissao permissao = permissaoService.atualizar(id, dto);
        PermissaoResponseDto responseDto = PermissaoMapper.toResponse(permissao);

        return ResponseEntity.status(200).body(responseDto);
    }

    @Operation(
            summary = "Deletar permissão",
            description = "Deleta uma permissão pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Permissão deletada com sucesso pelo ID"),
            @ApiResponse(responseCode = "404", description = "Permissão não encontrada pelo ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        permissaoService.deletar(id);

        return ResponseEntity.status(204).build();
    }

}

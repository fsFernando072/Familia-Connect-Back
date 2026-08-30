package school.sptech.FamiliaConnect.infraestructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.application.ports.in.AcessoUseCase;
import school.sptech.FamiliaConnect.infraestructure.web.dto.acesso.AcessoRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.acesso.AcessoResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.mapper.AcessoMapper;
import school.sptech.FamiliaConnect.domain.entity.Acesso;
import school.sptech.FamiliaConnect.application.service.AcessoService;

import java.util.List;

@Tag(name = "Acessos", description = "Operações relacionadas aos acessos às telas do sistema")
@RestController
@RequestMapping("/acessos")
public class AcessoController {

    private final AcessoUseCase acessoUseCase;

    public AcessoController(AcessoUseCase acessoUseCase) {
        this.acessoUseCase = acessoUseCase;
    }

    @Operation(
            summary = "Cadastrar um acesso",
            description = "Cadastra um nível de acesso com os dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Acesso cadastrado com sucesso")
    })
    @PostMapping
    @PreAuthorize("hasAuthority('cadastrar_acessos')")
    public ResponseEntity<AcessoResponseDto> cadastrar(@RequestBody @Valid AcessoRequestDto dto) {
        Acesso acesso = acessoUseCase.cadastrar(AcessoMapper.toModel(dto));
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
    @PreAuthorize("hasAuthority('listar_acessos')")
    public ResponseEntity<List<AcessoResponseDto>> listar() {
        List<Acesso> acessos = acessoUseCase.listar();

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
    @PreAuthorize("hasAuthority('listar_acessos')")
    public ResponseEntity<AcessoResponseDto> buscarPorId(@PathVariable Integer id) {
        Acesso acesso = acessoUseCase.buscarPorId(id);
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
    @PreAuthorize("hasAuthority('editar_acessos')")
    public ResponseEntity<AcessoResponseDto> atualizar(@PathVariable Integer id,
                                                       @RequestBody @Valid AcessoRequestDto dto) {
        Acesso acesso = acessoUseCase.atualizar(id, AcessoMapper.toModel(dto));
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
    @PreAuthorize("hasAuthority('excluir_acessos')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        acessoUseCase.deletar(id);

        return ResponseEntity.status(204).build();
    }
}

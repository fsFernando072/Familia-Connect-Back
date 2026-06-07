package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.Auditoria.AuditoriaRequestDto;
import school.sptech.FamiliaConnect.dto.Auditoria.AuditoriaResponseDto;
import school.sptech.FamiliaConnect.mapper.AcessoMapper;
import school.sptech.FamiliaConnect.mapper.AuditoriaMapper;
import school.sptech.FamiliaConnect.model.Acesso;
import school.sptech.FamiliaConnect.model.Auditoria;
import school.sptech.FamiliaConnect.service.AuditoriaService;

import java.util.List;

@Tag(name = "Auditorias", description = "Operações relacionadas às auditorias do sistema")
@RestController
@RequestMapping("/auditorias")
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    public AuditoriaController(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @Operation(
            summary = "Cadastrar auditoria",
            description = "Cadastra uma auditoria pelos dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Auditoria cadastrada com sucesso  "),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado pelo ID")
    })
    @PostMapping
    @PreAuthorize("hasAuthority('cadastrar_auditorias')")
    public ResponseEntity<AuditoriaResponseDto> cadastrar(@RequestBody @Valid AuditoriaRequestDto dto) {
        Auditoria auditoria = auditoriaService.cadastrar(AuditoriaMapper.toModel(dto));
        AuditoriaResponseDto responseDto = AuditoriaMapper.toResponse(auditoria);

        return ResponseEntity.status(201).body(responseDto);
    }

    @Operation(
            summary = "Listar auditorias",
            description = "Retorna uma lista com todas as auditorias do sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de auditorias retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de aditorias retornada vazia")
    })
    @GetMapping
    @PreAuthorize("hasAuthority('listar_auditorias')")
    public ResponseEntity<List<AuditoriaResponseDto>> listar() {
        List<Auditoria> auditorias = auditoriaService.listar();

        if (auditorias.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(AuditoriaMapper.toResponse(auditorias));
    }

    @Operation(
            summary = "Listar auditoria pelo ID",
            description = "Retorna a auditoria pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auditoria retornada com sucesso pelo ID"),
            @ApiResponse(responseCode = "404", description = "Auditoria não encontrada pelo ID")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('listar_auditorias')")
    public ResponseEntity<AuditoriaResponseDto> buscarPorId(@PathVariable Integer id) {
        Auditoria auditoria = auditoriaService.buscarPorId(id);
        AuditoriaResponseDto responseDto = AuditoriaMapper.toResponse(auditoria);

        return ResponseEntity.status(200).body(responseDto);
    }

    @Operation(
            summary = "Atualizar auditoria",
            description = "Atualiza uma auditoria pelos dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auditoria atualizada com sucesso pelo ID"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado (Auditoria ou Funcionário)")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('editar_auditorias')")
    public ResponseEntity<AuditoriaResponseDto> atualizar(@PathVariable Integer id,
                                                          @RequestBody @Valid AuditoriaRequestDto dto) {
        Auditoria auditoria = auditoriaService.atualizar(id, AuditoriaMapper.toModel(dto));
        AuditoriaResponseDto responseDto = AuditoriaMapper.toResponse(auditoria);

        return ResponseEntity.status(200).body(responseDto);
    }

    @Operation(
            summary = "Deletar auditoria",
            description = "Deleta uma auditoria pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auditoria deletada com sucesso pelo ID"),
            @ApiResponse(responseCode = "404", description = "Auditoria não encontrada pelo ID")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('excluir_auditorias')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        auditoriaService.deletar(id);

        return ResponseEntity.status(204).build();
    }

}

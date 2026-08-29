package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.historicoEstoque.HistoricoEstoqueRequestDto;
import school.sptech.FamiliaConnect.dto.historicoEstoque.HistoricoEstoqueResponseDto;
import school.sptech.FamiliaConnect.mapper.HistoricoEstoqueMapper;
import school.sptech.FamiliaConnect.model.HistoricoEstoque;
import school.sptech.FamiliaConnect.service.HistoricoEstoqueService;

import java.util.List;

@Tag(name = "Histórico de Estoque", description = "Operações relacionadas ao histórico mensal de estoque dos produtos")
@RestController
@RequestMapping("/historico-estoque")
public class HistoricoEstoqueController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final HistoricoEstoqueService historicoEstoqueService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public HistoricoEstoqueController(HistoricoEstoqueService historicoEstoqueService) {
        this.historicoEstoqueService = historicoEstoqueService;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Listar histórico de estoque",
            description = "Retorna uma lista com todos os registros de estoque cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de histórico de estoque retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de histórico de estoque retornada vazia")
    })
    @GetMapping
    @PreAuthorize("hasAuthority('listar_estoques')")
    public ResponseEntity<List<HistoricoEstoqueResponseDto>> listar() {

        List<HistoricoEstoque> historicos = historicoEstoqueService.listar();

        if (historicos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(HistoricoEstoqueMapper.toResponse(historicos));

    }

    @Operation(
            summary = "Listar histórico de estoque por id",
            description = "Retorna um registro de estoque pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Histórico de estoque retornado com sucesso pelo ID"),
            @ApiResponse(responseCode = "404", description = "Histórico de estoque não encontrado pelo ID")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('listar_estoques')")
    public ResponseEntity<HistoricoEstoqueResponseDto> listarPorId(@PathVariable Integer id) {

        HistoricoEstoque historicoEstoque = historicoEstoqueService.listarPorId(id);

        return ResponseEntity.status(200).body(HistoricoEstoqueMapper.toResponse(historicoEstoque));

    }

    @Operation(
            summary = "Cadastrar histórico de estoque",
            description = "Cadastra a quantidade de um produto em estoque para o mês atual"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Histórico de estoque cadastrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado pelo ID")
    })
    @PostMapping
    @PreAuthorize("hasAuthority('cadastrar_estoques')")
    public ResponseEntity<HistoricoEstoqueResponseDto> cadastrar(@RequestBody @Valid HistoricoEstoqueRequestDto requestDto) {

        HistoricoEstoque historicoEstoque = historicoEstoqueService.salvar(HistoricoEstoqueMapper.toModel(requestDto));

        return ResponseEntity.status(201).body(HistoricoEstoqueMapper.toResponse(historicoEstoque));

    }

    @Operation(
            summary = "Atualizar histórico de estoque",
            description = "Atualiza um registro de estoque pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Histórico de estoque atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Histórico de estoque ou produto não encontrado")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('editar_estoques')")
    public ResponseEntity<HistoricoEstoqueResponseDto> atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid HistoricoEstoqueRequestDto requestDto
    ) {

        HistoricoEstoque historicoEstoque = historicoEstoqueService.atualizar(id, HistoricoEstoqueMapper.toModel(requestDto));

        return ResponseEntity.status(200).body(HistoricoEstoqueMapper.toResponse(historicoEstoque));

    }

    @Operation(
            summary = "Deletar histórico de estoque",
            description = "Deleta um registro de estoque pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Histórico de estoque deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Histórico de estoque não encontrado pelo ID")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('excluir_estoques')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {

        historicoEstoqueService.deletar(id);

        return ResponseEntity.status(204).build();

    }

}

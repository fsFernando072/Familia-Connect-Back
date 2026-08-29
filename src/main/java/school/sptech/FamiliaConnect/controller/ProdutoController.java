package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.categoria.CategoriaRequestDto;
import school.sptech.FamiliaConnect.dto.categoria.CategoriaResponseDto;
import school.sptech.FamiliaConnect.dto.produto.ProdutoRequestDto;
import school.sptech.FamiliaConnect.dto.produto.ProdutoResponseDto;
import school.sptech.FamiliaConnect.mapper.CategoriaMapper;
import school.sptech.FamiliaConnect.mapper.ProdutoMapper;
import school.sptech.FamiliaConnect.model.Categoria;
import school.sptech.FamiliaConnect.model.Produto;
import school.sptech.FamiliaConnect.service.ProdutoService;

import java.util.List;

@Tag(name = "Produtos", description = "Operações relacionadas aos produtos")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private ProdutoService produtoService;

    // Construtor ------------------------------------------------------------------------------------------------------

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Listar produtos",
            description = "Retorna uma lista com todos os produtos cadastrados no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de produtos retornada vazia")
    })
    @GetMapping
    @PreAuthorize("hasAuthority('listar_produtos')")
    public ResponseEntity<List<ProdutoResponseDto>> listarProdutos(){

        List<Produto> produtos = produtoService.listar();

        if(produtos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(ProdutoMapper.toResponseList(produtos));

    }

    @Operation(
            summary = "Cadastrar produto",
            description = "Cadastra produto pelos dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria do produto não encontrada pelo ID")
    })
    @PostMapping
    @PreAuthorize("hasAuthority('cadastrar_produtos')")
    public ResponseEntity<ProdutoResponseDto> cadastrarProduto(@RequestBody @Valid ProdutoRequestDto requestDto){

        Produto produtoCadastrado = produtoService.salvar(ProdutoMapper.toModel(requestDto));

        return ResponseEntity.status(201).body(ProdutoMapper.toResponse(produtoCadastrado));

    }

    @Operation(
            summary = "Listar produto",
            description = "Retorna um produto realizada pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto encontrado com sucesso")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('listar_produtos')")
    public ResponseEntity<ProdutoResponseDto> listarProduto(@PathVariable Integer id){

        Produto produto = produtoService.listarPorId(id);

        return ResponseEntity.status(200).body(ProdutoMapper.toResponse(produto));

    }

    @Operation(
            summary = "Atualizar produto",
            description = "Atualiza produto pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('editar_produtos')")
    public ResponseEntity<ProdutoResponseDto> atualizarProduto(
            @PathVariable Integer id,
            @RequestBody @Valid ProdutoRequestDto requestDto
    ) {

        Produto produto = produtoService.atualizar(id, ProdutoMapper.toModel(requestDto));

        ProdutoResponseDto responseDto = ProdutoMapper.toResponse(produto);

        return ResponseEntity.ok(responseDto);
    }

    @Operation(
            summary = "Deletar produto",
            description = "Deleta um produto pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso pelo ID"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado pelo ID")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('excluir_produtos')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        produtoService.deletar(id);

        return ResponseEntity.status(204).build();
    }
}

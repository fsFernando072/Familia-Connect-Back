package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.produto.ProdutoRequestDto;
import school.sptech.FamiliaConnect.dto.produto.ProdutoResponseDto;
import school.sptech.FamiliaConnect.mapper.ProdutoMapper;
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
    public ResponseEntity<ProdutoResponseDto> cadastrarProduto(@RequestBody @Valid ProdutoRequestDto requestDto){

        Produto produtoCadastrado = produtoService.salvar(requestDto);

        return ResponseEntity.status(201).body(ProdutoMapper.toResponse(produtoCadastrado));

    }
}

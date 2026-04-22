package school.sptech.FamiliaConnect.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.produto.ProdutoRequestDto;
import school.sptech.FamiliaConnect.dto.produto.ProdutoResponseDto;
import school.sptech.FamiliaConnect.mapper.ProdutoMapper;
import school.sptech.FamiliaConnect.model.Produto;
import school.sptech.FamiliaConnect.service.ProdutoService;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listarProdutos(){

        List<Produto> produtos = produtoService.listar();

        if(produtos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(ProdutoMapper.toResponseList(produtos));

    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDto> cadastrarProduto(@RequestBody ProdutoRequestDto requestDto){

        Produto produtoCadastrado = produtoService.salvar(requestDto);

        return ResponseEntity.status(201).body(ProdutoMapper.toResponse(produtoCadastrado));

    }
}

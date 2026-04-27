package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.categoria.CategoriaRequestDto;
import school.sptech.FamiliaConnect.dto.categoria.CategoriaResponseDto;
import school.sptech.FamiliaConnect.mapper.CategoriaMapper;
import school.sptech.FamiliaConnect.model.Categoria;
import school.sptech.FamiliaConnect.service.CategoriaService;

import java.util.List;

@Tag(name = "Categorias", description = "Operações relacionadas às categorias dos produtos")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private CategoriaService categoriaService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Listar categorias",
            description = "Retorna uma lista das categorias dos produtos cadastradas no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de categorias retornada vazia")
    })
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> listarCategorias(){

        List<Categoria> categorias = categoriaService.listar();

        if(categorias.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(CategoriaMapper.toResponseList(categorias));
    }

    @Operation(
            summary = "Cadastrar categoria",
            description = "Cadastra uma categoria pelos dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria cadastrada com sucesso")
    })
    @PostMapping
    public ResponseEntity<CategoriaResponseDto> cadastrarCategoria(@RequestBody @Valid CategoriaRequestDto requestDto){

        Categoria categoria = CategoriaMapper.toModel(requestDto);

        Categoria categoriaCadastrada = categoriaService.salvar(categoria);

        return ResponseEntity.status(201).body(CategoriaMapper.toResponse(categoriaCadastrada));

    }
}

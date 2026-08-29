package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.cargo.CargoResponseDto;
import school.sptech.FamiliaConnect.dto.categoria.CategoriaRequestDto;
import school.sptech.FamiliaConnect.dto.categoria.CategoriaResponseDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaRequestDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaResponseDto;
import school.sptech.FamiliaConnect.mapper.CargoMapper;
import school.sptech.FamiliaConnect.mapper.CategoriaMapper;
import school.sptech.FamiliaConnect.mapper.EntregaMapper;
import school.sptech.FamiliaConnect.mapper.FamiliaMapper;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.model.Categoria;
import school.sptech.FamiliaConnect.model.Entrega;
import school.sptech.FamiliaConnect.model.Familia;
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
    @ApiResponses(value =    {
            @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de categorias retornada vazia")
    })
    @GetMapping
    @PreAuthorize("hasAuthority('listar_categorias')")
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
    @PreAuthorize("hasAuthority('cadastrar_categorias')")
    public ResponseEntity<CategoriaResponseDto> cadastrarCategoria(@RequestBody @Valid CategoriaRequestDto requestDto){

        Categoria categoria = CategoriaMapper.toModel(requestDto);

        Categoria categoriaCadastrada = categoriaService.salvar(categoria);

        return ResponseEntity.status(201).body(CategoriaMapper.toResponse(categoriaCadastrada));

    }

    @Operation(
            summary = "Listar categoria",
            description = "Retorna uma categoria realizada pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria cadastrada com sucesso")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('listar_categorias')")
    public ResponseEntity<CategoriaResponseDto> listarCategoria(@PathVariable Integer id){

        Categoria categoria = categoriaService.listarPorId(id);

        return ResponseEntity.status(200).body(CategoriaMapper.toResponse(categoria));

    }

    @Operation(
            summary = "Atualizar categoria",
            description = "Atualiza categoria pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('editar_categorias')")
    public ResponseEntity<CategoriaResponseDto> atualizarCategoria(
            @PathVariable Integer id,
            @RequestBody @Valid CategoriaRequestDto requestDto
    ) {

        Categoria categoria = categoriaService.atualizar(id, CategoriaMapper.toModel(requestDto));

        CategoriaResponseDto responseDto = CategoriaMapper.toResponse(categoria);

        return ResponseEntity.ok(responseDto);
    }

    @Operation(
            summary = "Deletar categoria",
            description = "Deleta uma categoria pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria deletada com sucesso pelo ID"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada pelo ID")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('excluir_categorias')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        categoriaService.deletar(id);

        return ResponseEntity.status(204).build();
    }
}

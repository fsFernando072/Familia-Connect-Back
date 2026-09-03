package school.sptech.FamiliaConnect.infraestructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.application.ports.in.CategoriaUseCase;
import school.sptech.FamiliaConnect.infraestructure.web.dto.categoria.CategoriaRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.categoria.CategoriaResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.mapper.CategoriaMapper;
import school.sptech.FamiliaConnect.domain.entity.Categoria;
import school.sptech.FamiliaConnect.application.service.CategoriaService;

import java.util.List;

@Tag(name = "Categorias", description = "Operações relacionadas às categorias dos produtos")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private CategoriaUseCase categoriaUseCase;

    // Construtores ----------------------------------------------------------------------------------------------------

    public CategoriaController(CategoriaUseCase categoriaUseCase) {
        this.categoriaUseCase = categoriaUseCase;
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

        List<Categoria> categorias = categoriaUseCase.listar();

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

        Categoria categoriaCadastrada = categoriaUseCase.salvar(categoria);

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

        Categoria categoria = categoriaUseCase.listarPorId(id);

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

        Categoria categoria = categoriaUseCase.atualizar(id, CategoriaMapper.toModel(requestDto));

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
        categoriaUseCase.deletar(id);

        return ResponseEntity.status(204).build();
    }
}

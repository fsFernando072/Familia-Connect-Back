package school.sptech.FamiliaConnect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.categoria.CategoriaRequestDto;
import school.sptech.FamiliaConnect.dto.categoria.CategoriaResponseDto;
import school.sptech.FamiliaConnect.mapper.CategoriaMapper;
import school.sptech.FamiliaConnect.model.Categoria;
import school.sptech.FamiliaConnect.service.CategoriaService;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> listarCategorias(){

        List<Categoria> categorias = categoriaService.listar();

        if(categorias.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(CategoriaMapper.toResponseList(categorias));
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> cadastrarCategoria(@RequestBody CategoriaRequestDto requestDto){

        Categoria categoria = CategoriaMapper.toModel(requestDto);

        Categoria categoriaCadastrada = categoriaService.salvar(categoria);

        return ResponseEntity.status(201).body(CategoriaMapper.toResponse(categoriaCadastrada));

    }
}

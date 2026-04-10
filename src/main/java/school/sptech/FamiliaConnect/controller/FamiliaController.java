package school.sptech.FamiliaConnect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.familia.FamiliaRequestDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaResponseDto;
import school.sptech.FamiliaConnect.mapper.FamiliaMapper;
import school.sptech.FamiliaConnect.model.Familia;
import school.sptech.FamiliaConnect.service.FamiliaService;

import java.util.List;

@RestController
@RequestMapping("/familias")
public class FamiliaController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final FamiliaService familiaService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public FamiliaController(FamiliaService familiaService) {
        this.familiaService = familiaService;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @PostMapping
    public ResponseEntity<FamiliaResponseDto> cadastrarFamilia(@RequestBody FamiliaRequestDto familiaRequestDto){

        Familia familia = FamiliaMapper.toModel(familiaRequestDto);

        Familia familiaCadastrada = familiaService.salvar(familia, familiaRequestDto.getEnderecoId());

        FamiliaResponseDto familiaResponseDto = FamiliaMapper.toResponse(familiaCadastrada);

        return ResponseEntity.status(201).body(familiaResponseDto);

    }

    @GetMapping
    public ResponseEntity<List<FamiliaResponseDto>> listarFamilias(){

        List<Familia> familias = familiaService.listar();

        List<FamiliaResponseDto> familiasResponseDto = FamiliaMapper.toResponseList(familias);

        return ResponseEntity.status(200).body(familiasResponseDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<FamiliaResponseDto> atualizarFamilia(@PathVariable Integer idFamilia) {

        Familia familia = familiaService.listarPorId(idFamilia);

        FamiliaResponseDto familiaResponseDto = FamiliaMapper.toResponse(familia);

        return ResponseEntity.status(200).body(familiaResponseDto);

    }
}

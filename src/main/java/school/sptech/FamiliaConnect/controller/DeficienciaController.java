package school.sptech.FamiliaConnect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.deficiencia.DeficienciaRequestDto;
import school.sptech.FamiliaConnect.dto.deficiencia.DeficienciaResponseDto;
import school.sptech.FamiliaConnect.mapper.DeficienciaMapper;
import school.sptech.FamiliaConnect.model.Deficiencia;
import school.sptech.FamiliaConnect.service.DeficienciaService;

import java.util.List;

@RestController
@RequestMapping("/deficiencias")
public class DeficienciaController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final DeficienciaService deficienciaService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public DeficienciaController(DeficienciaService deficienciaService) {
        this.deficienciaService = deficienciaService;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @PostMapping
    public ResponseEntity<DeficienciaResponseDto> cadastrarProfissao(@RequestBody DeficienciaRequestDto deficienciaRequestDto){

        Deficiencia deficienciaSalva = deficienciaService.salvarDeficiencia(deficienciaRequestDto);

        DeficienciaResponseDto responseDto = DeficienciaMapper.toResponse(deficienciaSalva);

        return ResponseEntity.status(201).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<DeficienciaResponseDto>> buscarProfissoes(){

        List<Deficiencia> deficiencias = deficienciaService.listarDeficiencias();

        if (deficiencias.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(DeficienciaMapper.toResponse(deficiencias));

    }
}

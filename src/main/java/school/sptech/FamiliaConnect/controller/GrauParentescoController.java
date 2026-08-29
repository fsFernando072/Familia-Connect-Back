package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.FamiliaConnect.dto.grauParentesco.GrauParentescoResponseDto;
import school.sptech.FamiliaConnect.mapper.GrauParentescoMapper;
import school.sptech.FamiliaConnect.model.GrauParentesco;
import school.sptech.FamiliaConnect.service.GrauParentescoService;

import java.util.List;

@Tag(name = "Graus de Parentesco", description = "Operações relacionadas aos graus de parentesco dos dependentes")
@RestController
@RequestMapping("/grau-parentescos")
public class GrauParentescoController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final GrauParentescoService grauParentescoService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public GrauParentescoController(GrauParentescoService grauParentescoService) {
        this.grauParentescoService = grauParentescoService;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Listar graus de parentesco",
            description = "Retorna uma lista com todos os graus de parentesco cadastrados no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de graus de parentesco retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de graus de parentesco retornada vazia")
    })
    @GetMapping
    @PreAuthorize("hasAuthority('listar_familias')")
    public ResponseEntity<List<GrauParentescoResponseDto>> listarGrauParentesco() {

        List<GrauParentesco> grausParentesco = grauParentescoService.listarGrauParentesco();

        if (grausParentesco.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(GrauParentescoMapper.toResponse(grausParentesco));
    }

}

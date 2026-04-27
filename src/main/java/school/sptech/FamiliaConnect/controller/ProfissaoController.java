package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.profissao.ProfissaoRequestDto;
import school.sptech.FamiliaConnect.dto.profissao.ProfissaoResponseDto;
import school.sptech.FamiliaConnect.mapper.ProfissaoMapper;
import school.sptech.FamiliaConnect.model.Profissao;
import school.sptech.FamiliaConnect.service.ProfissaoService;

import java.util.List;

@Tag(name = "Profissões", description = "Operações relacionadas às profissões")
@RestController
@RequestMapping("/profissoes")
public class ProfissaoController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final ProfissaoService profissaoService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public ProfissaoController(ProfissaoService profissaoService) {
        this.profissaoService = profissaoService;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Cadastrar profissão",
            description = "Cadastra uma nova profissão pelos dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profissão cadastrada com sucesso"),
            @ApiResponse(responseCode = "409", description = "Profissão já cadastrada")
    })
    @PostMapping
    public ResponseEntity<ProfissaoResponseDto> cadastrarProfissao(@RequestBody @Valid ProfissaoRequestDto profissaoRequestDto){

        Profissao profissaoSalva = profissaoService.cadastrarProfissao(profissaoRequestDto);

        ProfissaoResponseDto profissaoResponseDto = ProfissaoMapper.toResponse(profissaoSalva);

        return ResponseEntity.status(201).body(profissaoResponseDto);
    }

    @Operation(
            summary = "Listar profissões",
            description = "Retorna uma lista com todas as profissões cadastradas no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de profissões retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de profissões retornada vazia")
    })
    @GetMapping
    public ResponseEntity<List<ProfissaoResponseDto>> buscarProfissoes(){

        List<Profissao> profissoes = profissaoService.listarProfissoes();

        if (profissoes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(ProfissaoMapper.toResponse(profissoes));

    }

}

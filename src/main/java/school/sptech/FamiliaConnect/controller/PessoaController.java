package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.pessoa.PessoaRequestDto;
import school.sptech.FamiliaConnect.dto.pessoa.PessoaResponseDto;
import school.sptech.FamiliaConnect.mapper.PessoaMapper;
import school.sptech.FamiliaConnect.model.Pessoa;
import school.sptech.FamiliaConnect.service.PessoaService;

import java.util.List;

@Tag(name = "Pessoas", description = "Operações relacionadas às pessoas")
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final PessoaService pessoaService;

    // Construtores ---------------------------------------------------------------------------------------------------

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Cadastrar pessoa",
            description = "Cadastra uma pessoa pelos dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa cadastrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Família não encontrada pelo ID"),
            @ApiResponse(responseCode = "409", description = "Pessoa já cadastrada")
    })
    @PostMapping
    public ResponseEntity<PessoaResponseDto> cadastrarPessoa(@RequestBody @Valid PessoaRequestDto pessoaRequestDto){

        Pessoa pessoaCadastrada = pessoaService.salvar(pessoaRequestDto);

        PessoaResponseDto pessoaResponseDto = PessoaMapper.toResponse(pessoaCadastrada);

        return ResponseEntity.status(201).body(pessoaResponseDto);

    }
}

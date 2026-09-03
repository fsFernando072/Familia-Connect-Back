package school.sptech.FamiliaConnect.infraestructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.application.ports.in.PessoaUseCase;
import school.sptech.FamiliaConnect.infraestructure.web.dto.pessoa.PessoaRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.pessoa.PessoaResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.mapper.PessoaMapper;
import school.sptech.FamiliaConnect.domain.entity.Pessoa;
import school.sptech.FamiliaConnect.application.service.PessoaService;

@Tag(name = "Pessoas", description = "Operações relacionadas às pessoas")
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final PessoaUseCase pessoaUseCase;

    // Construtores ---------------------------------------------------------------------------------------------------

    public PessoaController(PessoaUseCase pessoaUseCase) {
        this.pessoaUseCase = pessoaUseCase;
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
    @PreAuthorize("hasAuthority('cadastrar_familias')")
    public ResponseEntity<PessoaResponseDto> cadastrarPessoa(@RequestBody @Valid PessoaRequestDto pessoaRequestDto){

        Pessoa pessoaCadastrada = pessoaUseCase.salvar(PessoaMapper.toModel(pessoaRequestDto));

        PessoaResponseDto pessoaResponseDto = PessoaMapper.toResponse(pessoaCadastrada);

        return ResponseEntity.status(201).body(pessoaResponseDto);

    }
}

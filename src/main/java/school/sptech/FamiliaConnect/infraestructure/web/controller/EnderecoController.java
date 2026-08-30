package school.sptech.FamiliaConnect.infraestructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.FamiliaConnect.application.ports.in.EnderecoUseCase;
import school.sptech.FamiliaConnect.infraestructure.web.dto.endereco.EnderecoRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.endereco.EnderecoResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.mapper.EnderecoMapper;
import school.sptech.FamiliaConnect.domain.entity.Endereco;
import school.sptech.FamiliaConnect.application.service.EnderecoService;

@Tag(name = "Endereços", description = "Operações relacionadas aos endereços das famílias")
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final EnderecoUseCase enderecoUseCase;

    // Construtores ----------------------------------------------------------------------------------------------------

    public EnderecoController(EnderecoUseCase enderecoUseCase) {
        this.enderecoUseCase = enderecoUseCase;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Cadastrar endereço",
            description = "Cadastra um endereço com os dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço cadastrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado pelo ID"),
            @ApiResponse(responseCode = "409", description = "Endereço já cadastrado")
    })
    @PostMapping
    @PreAuthorize("hasAuthority('cadastrar_funcionarios')")
    public ResponseEntity<EnderecoResponseDto> salvar(@RequestBody @Valid EnderecoRequestDto enderecoRequestDto){

        Endereco endereco = enderecoUseCase.salvar(EnderecoMapper.toModel(enderecoRequestDto));

        EnderecoResponseDto responseDto = EnderecoMapper.toResponse(endereco);

        return ResponseEntity.status(201).body(responseDto);

    }
}

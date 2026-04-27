package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.FamiliaConnect.dto.endereco.EnderecoRequestDto;
import school.sptech.FamiliaConnect.dto.endereco.EnderecoResponseDto;
import school.sptech.FamiliaConnect.mapper.EnderecoMapper;
import school.sptech.FamiliaConnect.model.Endereco;
import school.sptech.FamiliaConnect.service.EnderecoService;

@Tag(name = "Endereços", description = "Operações relacionadas aos endereços das famílias")
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final EnderecoService enderecoService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
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
    public ResponseEntity<EnderecoResponseDto> salvar(@RequestBody @Valid EnderecoRequestDto enderecoRequestDto){

        Endereco endereco = enderecoService.salvar(enderecoRequestDto);

        EnderecoResponseDto responseDto = EnderecoMapper.toResponse(endereco);

        return ResponseEntity.status(201).body(responseDto);

    }
}

package school.sptech.FamiliaConnect.infraestructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.FamiliaConnect.application.ports.in.CepUseCase;
import school.sptech.FamiliaConnect.domain.entity.EnderecoCep;
import school.sptech.FamiliaConnect.infraestructure.web.dto.cep.EnderecoCepResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.mapper.CepMapper;

@Tag(name = "CEP", description = "Consulta de endereço a partir do CEP")
@RestController
@RequestMapping("/cep")
public class CepController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final CepUseCase cepUseCase;

    // Construtores ----------------------------------------------------------------------------------------------------

    public CepController(CepUseCase cepUseCase) {
        this.cepUseCase = cepUseCase;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Buscar endereço por CEP",
            description = "Consulta, em um provedor externo (ViaCEP), o endereço correspondente ao CEP informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "CEP com formato inválido"),
            @ApiResponse(responseCode = "404", description = "Nenhum endereço encontrado para o CEP informado"),
    })
    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoCepResponseDto> buscarPorCep(@PathVariable String cep) {
        EnderecoCep enderecoCep = cepUseCase.buscarEnderecoPorCep(cep);

        return ResponseEntity.ok(CepMapper.toResponse(enderecoCep));
    }
}

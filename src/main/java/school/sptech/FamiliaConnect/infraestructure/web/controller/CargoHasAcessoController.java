package school.sptech.FamiliaConnect.infraestructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.application.ports.in.CargoHasAcessoUseCase;
import school.sptech.FamiliaConnect.infraestructure.web.dto.cargoHasAcesso.CargoHasAcessoRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.cargoHasAcesso.CargoHasAcessoResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.mapper.CargoHasAcessoMapper;
import school.sptech.FamiliaConnect.domain.entity.CargoHasAcesso;
import school.sptech.FamiliaConnect.application.service.CargoHasAcessoService;

import java.util.List;

@Tag(name = "Cargos-Acessos", description = "Operações relacionadas aos acessos de cada cargo")
@RestController
@RequestMapping("/cargos-acessos")
public class CargoHasAcessoController {

    private final CargoHasAcessoUseCase cargoHasAcessoUseCase;

    public CargoHasAcessoController(CargoHasAcessoUseCase cargoHasAcessoUseCase) {
        this.cargoHasAcessoUseCase = cargoHasAcessoUseCase;
    }

    @Operation(
            summary = "Cadastrar um acesso de um cargo",
            description = "Cadastra um acesso de um cargo com os dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Acesso do cargo cadastrado com sucesso")
    })
    @PostMapping
//    @PreAuthorize("hasAuthority('cadastrar_cargos')")
    public ResponseEntity<CargoHasAcessoResponseDto> cadastrar(@RequestBody @Valid CargoHasAcessoRequestDto dto) {
        CargoHasAcesso cargoHasAcesso = cargoHasAcessoUseCase.cadastrar(CargoHasAcessoMapper.toModel(dto));
        CargoHasAcessoResponseDto responseDto = CargoHasAcessoMapper.toResponse(cargoHasAcesso);

        return ResponseEntity.status(201).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<CargoHasAcessoResponseDto>> listar() {
        List<CargoHasAcesso> cargosHasAcesso = cargoHasAcessoUseCase.listar();

        if (cargosHasAcesso.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(CargoHasAcessoMapper.toResponse(cargosHasAcesso));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoHasAcessoResponseDto> buscarPorId(@PathVariable Integer id) {
        CargoHasAcesso cargoHasAcesso = cargoHasAcessoUseCase.buscarPorId(id);
        CargoHasAcessoResponseDto responseDto = CargoHasAcessoMapper.toResponse(cargoHasAcesso);

        return ResponseEntity.status(200).body(responseDto);
    }

    @Operation(
            summary = "Atualizar acesso do cargo",
            description = "Atualiza acesso do cargo pelos dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acesso do cargo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Acesso do cargo não encontrado pelo ID")
    })
    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('editar_cargos')")
    public ResponseEntity<CargoHasAcessoResponseDto> atualizar(@PathVariable Integer id,
                                                               @RequestBody @Valid CargoHasAcessoRequestDto dto) {
        CargoHasAcesso cargoHasAcesso = cargoHasAcessoUseCase.atualizar(id, CargoHasAcessoMapper.toModel(dto));
        CargoHasAcessoResponseDto responseDto = CargoHasAcessoMapper.toResponse(cargoHasAcesso);

        return ResponseEntity.status(200).body(responseDto);
    }

    @Operation(
            summary = "Deletar o acesso de um cargo",
            description = "Deleta um acesso de um cargo pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acesso do cargo deletado com sucesso pelo ID"),
            @ApiResponse(responseCode = "404", description = "Acesso do cargo não encontrado pelo ID")
    })
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('excluir_cargos')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        cargoHasAcessoUseCase.deletar(id);

        return ResponseEntity.status(204).build();
    }

}

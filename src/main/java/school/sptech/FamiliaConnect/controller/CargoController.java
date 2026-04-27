package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.Cargo.CargoRequestDto;
import school.sptech.FamiliaConnect.dto.Cargo.CargoResponseDto;
import school.sptech.FamiliaConnect.mapper.CargoMapper;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.service.CargoService;

import java.util.List;

@Tag(name = "Cargos", description = "Operações relacionadas aos cargos dos funcionários")
@RestController
@RequestMapping("/cargos")
public class CargoController {

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @Operation(
            summary = "Cadastrar um cargo",
            description = "Cadastra um cargo com os dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cargo cadastrado com sucesso")
    })
    @PostMapping
    public ResponseEntity<CargoResponseDto> cadastrar(@RequestBody @Valid CargoRequestDto dto) {
        Cargo cargo = cargoService.cadastrar(dto);
        CargoResponseDto responseDto = CargoMapper.toResponse(cargo);

        return ResponseEntity.status(201).body(responseDto);
    }

    @Operation(
            summary = "Listar cargos",
            description = "Retorna uma lista com todos os cargos cadastrados no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cargos retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de cargos retornada vazia")
    })
    @GetMapping
    public ResponseEntity<List<CargoResponseDto>> listar() {
        List<Cargo> cargos = cargoService.listar();

        if (cargos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(CargoMapper.toResponse(cargos));
    }

    @Operation(
            summary = "Atualizar cargo",
            description = "Atualiza um cargo pelos dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cargo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cargo não encontrado pelo ID")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CargoResponseDto> atualizar(@PathVariable Integer id,
                                                      @RequestBody @Valid CargoRequestDto dto) {
        Cargo cargo = cargoService.atualizar(id, dto);
        CargoResponseDto responseDto = CargoMapper.toResponse(cargo);

        return ResponseEntity.status(200).body(responseDto);
    }

    @Operation(
            summary = "Listar cargo",
            description = "Retorna um cargo pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cargo retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cargo não encontrado pelo ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CargoResponseDto> buscarPorId(@PathVariable Integer id) {
        Cargo cargo = cargoService.buscarPorId(id);
        CargoResponseDto responseDto = CargoMapper.toResponse(cargo);

        return ResponseEntity.status(200).body(responseDto);
    }

    @Operation(
            summary = "Deletar cargo",
            description = "Deleta um cargo pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cargo deletado com sucesso pelo ID"),
            @ApiResponse(responseCode = "404", description = "Cargo não encontrado pelo ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        cargoService.deletar(id);

        return ResponseEntity.status(204).build();
    }

}

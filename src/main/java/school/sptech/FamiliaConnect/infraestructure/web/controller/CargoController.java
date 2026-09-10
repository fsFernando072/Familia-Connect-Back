package school.sptech.FamiliaConnect.infraestructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.application.ports.in.CargoUseCase;
import school.sptech.FamiliaConnect.infraestructure.web.dto.cargo.CargoRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.cargo.CargoResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.mapper.CargoMapper;
import school.sptech.FamiliaConnect.domain.entity.Cargo;
import school.sptech.FamiliaConnect.application.service.CargoService;

@Tag(name = "Cargos", description = "Operações relacionadas aos cargos dos funcionários")
@RestController
@RequestMapping("/cargos")
public class CargoController {

    private final CargoUseCase cargoUseCase;

    public CargoController(CargoUseCase cargoUseCase) {
        this.cargoUseCase = cargoUseCase;
    }

    @Operation(
            summary = "Cadastrar um cargo",
            description = "Cadastra um cargo com os dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cargo cadastrado com sucesso")
    })
    @PostMapping
    @PreAuthorize("hasAuthority('cadastrar_cargos')")
    public ResponseEntity<CargoResponseDto> cadastrar(@RequestBody @Valid CargoRequestDto dto) {
        Cargo cargo = cargoUseCase.cadastrar( CargoMapper.toModel(dto));
        CargoResponseDto responseDto = CargoMapper.toResponse(cargo);

        return ResponseEntity.status(201).body(responseDto);
    }

    @Operation(
            summary = "Listar cargos",
            description = "Retorna uma lista paginada dos cargos cadastrados no sistema, " +
                    "com pesquisa opcional pelo nome (case insensitive) e ordenação opcional pelo nome"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cargos retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de cargos retornada vazia")
    })
    @GetMapping
    @PreAuthorize("hasAuthority('listar_cargos')")
    public ResponseEntity<Page<CargoResponseDto>> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "asc") String direcao
    ) {
        Sort.Direction direcaoOrdenacao = Sort.Direction.fromString(direcao);
        Sort ordenacao = Sort.by(direcaoOrdenacao, "nome", "id");
        Pageable pageable = PageRequest.of(page, size, ordenacao);

        Page<Cargo> cargos = cargoUseCase.listar(nome, pageable);

        if (cargos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(cargos.map(CargoMapper::toResponse));
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
    @PreAuthorize("hasAuthority('editar_cargos')")
    public ResponseEntity<CargoResponseDto> atualizar(@PathVariable Integer id,
                                                      @RequestBody @Valid CargoRequestDto dto) {
        Cargo cargo = cargoUseCase.atualizar(id, CargoMapper.toModel(dto));
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
    @PreAuthorize("hasAuthority('listar_cargos')")
    public ResponseEntity<CargoResponseDto> buscarPorId(@PathVariable Integer id) {
        Cargo cargo = cargoUseCase.buscarPorId(id);
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
    @PreAuthorize("hasAuthority('excluir_cargos')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        cargoUseCase.deletar(id);

        return ResponseEntity.status(204).build();
    }

}

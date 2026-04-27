package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.CargoHasAcesso.CargoHasAcessoRequestDto;
import school.sptech.FamiliaConnect.dto.CargoHasAcesso.CargoHasAcessoResponseDto;
import school.sptech.FamiliaConnect.mapper.CargoHasAcessoMapper;
import school.sptech.FamiliaConnect.mapper.CargoMapper;
import school.sptech.FamiliaConnect.model.CargoHasAcesso;
import school.sptech.FamiliaConnect.service.CargoHasAcessoService;

import java.util.List;
import java.util.Optional;

@Tag(name = "Cargos-Acessos", description = "Operações relacionadas aos acessos de cada cargo")
@RestController
@RequestMapping("/cargos-acessos")
public class CargoHasAcessoController {

    private final CargoHasAcessoService cargoHasAcessoService;

    public CargoHasAcessoController(CargoHasAcessoService cargoHasAcessoService) {
        this.cargoHasAcessoService = cargoHasAcessoService;
    }

    @PostMapping
    public ResponseEntity<CargoHasAcessoResponseDto> cadastrar(@RequestBody @Valid CargoHasAcessoRequestDto dto) {
        CargoHasAcesso cargoHasAcesso = cargoHasAcessoService.cadastrar(dto);
        CargoHasAcessoResponseDto responseDto = CargoHasAcessoMapper.toResponse(cargoHasAcesso);

        return ResponseEntity.status(201).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<CargoHasAcessoResponseDto>> listar() {
        List<CargoHasAcesso> cargosHasAcesso = cargoHasAcessoService.listar();

        if (cargosHasAcesso.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(CargoHasAcessoMapper.toResponse(cargosHasAcesso));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoHasAcessoResponseDto> buscarPorId(@PathVariable Integer id) {
        CargoHasAcesso cargoHasAcesso = cargoHasAcessoService.buscarPorId(id);
        CargoHasAcessoResponseDto responseDto = CargoHasAcessoMapper.toResponse(cargoHasAcesso);

        return ResponseEntity.status(200).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargoHasAcessoResponseDto> atualizar(@PathVariable Integer id,
                                                               @RequestBody @Valid CargoHasAcessoRequestDto dto) {
        CargoHasAcesso cargoHasAcesso = cargoHasAcessoService.atualizar(id, dto);
        CargoHasAcessoResponseDto responseDto = CargoHasAcessoMapper.toResponse(cargoHasAcesso);

        return ResponseEntity.status(200).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        cargoHasAcessoService.deletar(id);

        return ResponseEntity.status(204).build();
    }

}

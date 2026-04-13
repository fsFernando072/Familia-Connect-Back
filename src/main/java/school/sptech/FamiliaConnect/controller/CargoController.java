package school.sptech.FamiliaConnect.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.Cargo.CargoRequestDto;
import school.sptech.FamiliaConnect.dto.Cargo.CargoResponseDto;
import school.sptech.FamiliaConnect.mapper.CargoMapper;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.service.CargoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cargos")
public class CargoController {

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @PostMapping
    public ResponseEntity<CargoResponseDto> cadastrar(@RequestBody @Valid CargoRequestDto dto) {
        Cargo cargo = cargoService.cadastrar(dto);
        return ResponseEntity.status(201).body(CargoMapper.toResponse(cargo));
    }

    @GetMapping
    public ResponseEntity<List<CargoResponseDto>> listar() {
        List<Cargo> cargos = cargoService.listar();

        if (cargos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(CargoMapper.toResponseList(cargos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoResponseDto> buscarPorId(@PathVariable Integer id) {
        Optional<Cargo> cargoOpt = cargoService.buscarPorId(id);

        if (cargoOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(CargoMapper.toResponse(cargoOpt.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargoResponseDto> atualizar(@PathVariable Integer id,
                                                      @RequestBody @Valid CargoRequestDto dto) {
        Optional<Cargo> cargoOpt = cargoService.atualizar(id, dto);

        if (cargoOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(CargoMapper.toResponse(cargoOpt.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean deletou = cargoService.deletar(id);

        if (!deletou) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(204).build();
    }

}

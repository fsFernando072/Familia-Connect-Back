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
        CargoResponseDto responseDto = CargoMapper.toResponse(cargo);

        return ResponseEntity.status(201).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<CargoResponseDto>> listar() {
        List<Cargo> cargos = cargoService.listar();

        if (cargos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(CargoMapper.toResponse(cargos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoResponseDto> buscarPorId(@PathVariable Integer id) {
        Cargo cargo = cargoService.buscarPorId(id);
        CargoResponseDto responseDto = CargoMapper.toResponse(cargo);

        return ResponseEntity.status(200).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargoResponseDto> atualizar(@PathVariable Integer id,
                                                      @RequestBody @Valid CargoRequestDto dto) {
        Cargo cargo = cargoService.atualizar(id, dto);
        CargoResponseDto responseDto = CargoMapper.toResponse(cargo);

        return ResponseEntity.status(200).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        cargoService.deletar(id);

        return ResponseEntity.status(204).build();
    }

}

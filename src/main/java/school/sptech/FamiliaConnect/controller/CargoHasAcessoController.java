package school.sptech.FamiliaConnect.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.CargoHasAcesso.CargoHasAcessoRequestDto;
import school.sptech.FamiliaConnect.dto.CargoHasAcesso.CargoHasAcessoResponseDto;
import school.sptech.FamiliaConnect.mapper.CargoHasAcessoMapper;
import school.sptech.FamiliaConnect.model.CargoHasAcesso;
import school.sptech.FamiliaConnect.service.CargoHasAcessoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cargos-acessos")
public class CargoHasAcessoController {

    private final CargoHasAcessoService cargoHasAcessoService;

    public CargoHasAcessoController(CargoHasAcessoService cargoHasAcessoService) {
        this.cargoHasAcessoService = cargoHasAcessoService;
    }

    @PostMapping
    public ResponseEntity<CargoHasAcessoResponseDto> cadastrar(@RequestBody @Valid CargoHasAcessoRequestDto dto) {
        Optional<CargoHasAcesso> cargoHasAcessoOpt = cargoHasAcessoService.cadastrar(dto);

        if (cargoHasAcessoOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(201).body(CargoHasAcessoMapper.toResponse(cargoHasAcessoOpt.get()));
    }

    @GetMapping
    public ResponseEntity<List<CargoHasAcessoResponseDto>> listar() {
        List<CargoHasAcesso> cargosHasAcesso = cargoHasAcessoService.listar();

        if (cargosHasAcesso.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(CargoHasAcessoMapper.toResponseList(cargosHasAcesso));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoHasAcessoResponseDto> buscarPorId(@PathVariable Integer id) {
        Optional<CargoHasAcesso> cargoHasAcessoOpt = cargoHasAcessoService.buscarPorId(id);

        if (cargoHasAcessoOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(CargoHasAcessoMapper.toResponse(cargoHasAcessoOpt.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargoHasAcessoResponseDto> atualizar(@PathVariable Integer id,
                                                               @RequestBody @Valid CargoHasAcessoRequestDto dto) {
        Optional<CargoHasAcesso> cargoHasAcessoOpt = cargoHasAcessoService.atualizar(id, dto);

        if (cargoHasAcessoOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(CargoHasAcessoMapper.toResponse(cargoHasAcessoOpt.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean deletou = cargoHasAcessoService.deletar(id);

        if (!deletou) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(204).build();
    }

}

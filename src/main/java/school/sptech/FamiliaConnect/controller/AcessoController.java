package school.sptech.FamiliaConnect.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.Acesso.AcessoRequestDto;
import school.sptech.FamiliaConnect.dto.Acesso.AcessoResponseDto;
import school.sptech.FamiliaConnect.mapper.AcessoMapper;
import school.sptech.FamiliaConnect.model.Acesso;
import school.sptech.FamiliaConnect.service.AcessoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acessos")
public class AcessoController {

    private final AcessoService acessoService;

    public AcessoController(AcessoService acessoService) {
        this.acessoService = acessoService;
    }

    @PostMapping
    public ResponseEntity<AcessoResponseDto> cadastrar(@RequestBody @Valid AcessoRequestDto dto) {
        Acesso acesso = acessoService.cadastrar(dto);
        AcessoResponseDto responseDto = AcessoMapper.toResponse(acesso);

        return ResponseEntity.status(201).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<AcessoResponseDto>> listar() {
        List<Acesso> acessos = acessoService.listar();

        if (acessos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(AcessoMapper.toResponse(acessos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcessoResponseDto> buscarPorId(@PathVariable Integer id) {
        Acesso acesso = acessoService.buscarPorId(id);
        AcessoResponseDto responseDto = AcessoMapper.toResponse(acesso);

        return ResponseEntity.status(200).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcessoResponseDto> atualizar(@PathVariable Integer id,
                                                       @RequestBody @Valid AcessoRequestDto dto) {
        Acesso acesso = acessoService.atualizar(id, dto);
        AcessoResponseDto responseDto = AcessoMapper.toResponse(acesso);

        return ResponseEntity.status(200).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        acessoService.deletar(id);

        return ResponseEntity.status(204).build();
    }
}

package school.sptech.FamiliaConnect.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.Permissao.PermissaoRequestDto;
import school.sptech.FamiliaConnect.dto.Permissao.PermissaoResponseDto;
import school.sptech.FamiliaConnect.mapper.PermissaoMapper;
import school.sptech.FamiliaConnect.model.Permissao;
import school.sptech.FamiliaConnect.service.PermissaoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    private final PermissaoService permissaoService;

    public PermissaoController(PermissaoService permissaoService) {
        this.permissaoService = permissaoService;
    }

    @PostMapping
    public ResponseEntity<PermissaoResponseDto> cadastrar(@RequestBody @Valid PermissaoRequestDto dto) {
        Permissao permissao = permissaoService.cadastrar(dto);
        return ResponseEntity.status(201).body(PermissaoMapper.toResponse(permissao));
    }

    @GetMapping
    public ResponseEntity<List<PermissaoResponseDto>> listar() {
        List<Permissao> permissoes = permissaoService.listar();

        if (permissoes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(PermissaoMapper.toResponseList(permissoes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissaoResponseDto> buscarPorId(@PathVariable Integer id) {
        Optional<Permissao> permissaoOpt = permissaoService.buscarPorId(id);

        if (permissaoOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(PermissaoMapper.toResponse(permissaoOpt.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissaoResponseDto> atualizar(@PathVariable Integer id,
                                                          @RequestBody @Valid PermissaoRequestDto dto) {
        Optional<Permissao> permissaoOpt = permissaoService.atualizar(id, dto);

        if (permissaoOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(PermissaoMapper.toResponse(permissaoOpt.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean deletou = permissaoService.deletar(id);

        if (!deletou) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(204).build();
    }

}

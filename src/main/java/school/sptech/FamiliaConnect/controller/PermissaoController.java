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
        PermissaoResponseDto responseDto = PermissaoMapper.toResponse(permissao);

        return ResponseEntity.status(201).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PermissaoResponseDto>> listar() {
        List<Permissao> permissoes = permissaoService.listar();

        if (permissoes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(PermissaoMapper.toResponse(permissoes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissaoResponseDto> buscarPorId(@PathVariable Integer id) {
        Permissao permissao = permissaoService.buscarPorId(id);
        PermissaoResponseDto responseDto = PermissaoMapper.toResponse(permissao);

        return ResponseEntity.status(200).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissaoResponseDto> atualizar(@PathVariable Integer id,
                                                          @RequestBody @Valid PermissaoRequestDto dto) {
        Permissao permissao = permissaoService.atualizar(id, dto);
        PermissaoResponseDto responseDto = PermissaoMapper.toResponse(permissao);

        return ResponseEntity.status(200).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        permissaoService.deletar(id);

        return ResponseEntity.status(204).build();
    }

}

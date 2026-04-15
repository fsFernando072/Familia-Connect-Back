package school.sptech.FamiliaConnect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.profissao.ProfissaoRequestDto;
import school.sptech.FamiliaConnect.dto.profissao.ProfissaoResponseDto;
import school.sptech.FamiliaConnect.mapper.ProfissaoMapper;
import school.sptech.FamiliaConnect.model.Profissao;
import school.sptech.FamiliaConnect.service.ProfissaoService;

import java.util.List;

@RestController
@RequestMapping("/profissoes")
public class ProfissaoController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final ProfissaoService profissaoService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public ProfissaoController(ProfissaoService profissaoService) {
        this.profissaoService = profissaoService;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @PostMapping
    public ResponseEntity<ProfissaoResponseDto> cadastrarProfissao(@RequestBody ProfissaoRequestDto profissaoRequestDto){

        Profissao profissaoSalva = profissaoService.cadastrarProfissao(profissaoRequestDto);

        ProfissaoResponseDto profissaoResponseDto = ProfissaoMapper.toResponse(profissaoSalva);

        return ResponseEntity.status(201).body(profissaoResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ProfissaoResponseDto>> buscarProfissoes(){

        List<Profissao> profissoes = profissaoService.listarProfissoes();

        if (profissoes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(ProfissaoMapper.toResponse(profissoes));

    }

}

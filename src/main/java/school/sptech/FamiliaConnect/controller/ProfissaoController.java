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

        Profissao profissao = ProfissaoMapper.toModel(profissaoRequestDto);

        Profissao profissaoSalva = profissaoService.salvar(profissao);

        ProfissaoResponseDto profissaoResponseDto = ProfissaoMapper.toResponse(profissaoSalva);

        return ResponseEntity.status(201).body(profissaoResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ProfissaoResponseDto>> buscarProfissoes(){

        List<Profissao> profissoes = profissaoService.listarProfissoes();

        List<ProfissaoResponseDto> profissoesResponseDto = ProfissaoMapper.toResponseList(profissoes);

        return ResponseEntity.status(200).body(profissoesResponseDto);

    }

}

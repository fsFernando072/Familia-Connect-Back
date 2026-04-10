package school.sptech.FamiliaConnect.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.pessoa.PessoaRequestDto;
import school.sptech.FamiliaConnect.dto.pessoa.PessoaResponseDto;
import school.sptech.FamiliaConnect.mapper.PessoaMapper;
import school.sptech.FamiliaConnect.model.Pessoa;
import school.sptech.FamiliaConnect.service.PessoaService;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final PessoaService pessoaService;

    // Construtores ---------------------------------------------------------------------------------------------------

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @PostMapping
    public ResponseEntity<PessoaResponseDto> cadastrarPessoa(@RequestBody PessoaRequestDto pessoaRequestDto){

        Pessoa pessoa = PessoaMapper.toModel(pessoaRequestDto);

        Pessoa pessoaCadastrada = pessoaService.salvar(pessoa, pessoaRequestDto.getIdDeficiencia(), pessoaRequestDto.getIdFamilia(), pessoaRequestDto.getIdProfissao());

        PessoaResponseDto pessoaResponseDto = PessoaMapper.toResponse(pessoaCadastrada);

        return ResponseEntity.status(201).body(pessoaResponseDto);

    }
}

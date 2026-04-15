package school.sptech.FamiliaConnect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.FamiliaConnect.dto.endereco.EnderecoRequestDto;
import school.sptech.FamiliaConnect.dto.endereco.EnderecoResponseDto;
import school.sptech.FamiliaConnect.mapper.EnderecoMapper;
import school.sptech.FamiliaConnect.model.Endereco;
import school.sptech.FamiliaConnect.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final EnderecoService enderecoService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @PostMapping
    public ResponseEntity<EnderecoResponseDto> salvar(@RequestBody EnderecoRequestDto enderecoRequestDto){

        Endereco endereco = enderecoService.salvar(enderecoRequestDto);

        EnderecoResponseDto responseDto = EnderecoMapper.toResponse(endereco);

        return ResponseEntity.status(201).body(responseDto);

    }
}

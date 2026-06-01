package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.familia.FamiliaListResponseDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaRequestDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaResponseDto;
import school.sptech.FamiliaConnect.exception.DadosDaFamiliaAusenteException;
import school.sptech.FamiliaConnect.mapper.FamiliaMapper;
import school.sptech.FamiliaConnect.model.Familia;
import school.sptech.FamiliaConnect.model.Pessoa;
import school.sptech.FamiliaConnect.service.FamiliaService;

import java.util.List;

@Tag(name = "Famílias", description = "Operações relacionadas às famílias")
@RestController
@RequestMapping("/familias")
public class FamiliaController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final FamiliaService familiaService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public FamiliaController(FamiliaService familiaService) {
        this.familiaService = familiaService;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Cadastrar família",
            description = "Cadastra uma família pelos dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Família cadastrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado pelo ID")
    })
    @PostMapping
    @PreAuthorize("hasAuthority('cadastrar_familias')")
    public ResponseEntity<FamiliaResponseDto> cadastrarFamilia(@RequestBody @Valid FamiliaRequestDto familiaRequestDto){

        Familia familiaCadastrada = familiaService.salvar(familiaRequestDto);

        FamiliaResponseDto responseDto = FamiliaMapper.toResponse(familiaCadastrada);

        return ResponseEntity.status(201).body(responseDto);

    }

    @Operation(
            summary = "Listar famílias",
            description = "Retorna uma lista com todas as famílias cadastradas no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de famílias retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de famílias retornada vazia")
    })
    @GetMapping
    @PreAuthorize("hasAuthority('listar_familias')")
    public ResponseEntity<Page<FamiliaListResponseDto>> listarFamilias(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        Sort sort = Sort.by("dataCadastro").descending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        Page<FamiliaListResponseDto> familias = familiaService.listar(pageRequest);

        if (familias.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(familias);

    }

    @Operation(
            summary = "Listar família por ID",
            description = "Retorna os dados da familía com base no ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna informações da família com sucesso"),
            @ApiResponse(responseCode = "404", description = "Retorna erro ao achar família com o ID informado")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('listar_familias')")
    public ResponseEntity<List<Pessoa>> familiaById(@PathVariable Integer id){

        List<Pessoa> familia = familiaService.listarPorIdFamilia(id);

        return ResponseEntity.status(200).body(familia);

    }

//
//    @Operation(
//            summary = "Atualizar família",
//            description = "Atualiza uma família com os dados fornecidos pelo ID"
//    )
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Família atualizada com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Família não encontrada pelo ID")
//    })
//    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('editar_familias')")
//    public ResponseEntity<FamiliaResponseDto> atualizarFamilia(@PathVariable Integer idFamilia) {
//
//        Familia familia = familiaService.listarPorId(idFamilia);
//
//        FamiliaResponseDto responseDto = FamiliaMapper.toResponse(familia);
//
//        return ResponseEntity.status(200).body(responseDto);
//
//    }
}

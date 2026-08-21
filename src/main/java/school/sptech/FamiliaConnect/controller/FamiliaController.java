package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.dto.familia.FamiliaDetalhesResponseDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaListResponseDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaRequestDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaResponseDto;
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
            description = "Cadastra endereço, família, responsável e dependentes em uma única transação. " +
                    "Se qualquer etapa falhar (endereço duplicado, estado inexistente, CPF duplicado etc.), nada é salvo."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Família cadastrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado pelo ID"),
            @ApiResponse(responseCode = "409", description = "Endereço ou pessoa (CPF) já cadastrados")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('cadastrar_familias')")
    public ResponseEntity<FamiliaResponseDto> cadastrarFamilia(
            @RequestPart("familiaRequestDto") @Valid FamiliaRequestDto familiaRequestDto,
            @RequestPart(value = "arquivo", required = false) MultipartFile arquivo
    ){

        Familia familiaCadastrada = familiaService.salvar(familiaRequestDto, arquivo);

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
            summary = "Detalhar família",
            description = "Retorna os dados completos da família (endereço, responsável e dependentes) conforme o ID informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados da família retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Família com o ID informado não identificada")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('listar_familias')")
    public ResponseEntity<FamiliaDetalhesResponseDto> detalharFamilia(@PathVariable Integer id){

        Familia familia = familiaService.listarPorId(id);
        List<Pessoa> integrantes = familiaService.listarIntegrantes(id);

        FamiliaDetalhesResponseDto responseDto = FamiliaMapper.toDetalhes(familia, integrantes);

        return ResponseEntity.status(200).body(responseDto);

    }

    @Operation(
            summary = "Atualizar família",
            description = "Atualiza endereço, família, responsável e dependentes de uma família em uma única transação"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Família atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Família, endereço, estado ou responsável não encontrados"),
            @ApiResponse(responseCode = "409", description = "CPF já cadastrado para outra pessoa")
    })
    @PutMapping(value = "/{idFamilia}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('editar_familias')")
    public ResponseEntity<FamiliaResponseDto> atualizarFamilia(
            @PathVariable Integer idFamilia,
            @RequestPart("familiaRequestDto") @Valid FamiliaRequestDto requestDto,
            @RequestPart(value = "arquivo", required = false) MultipartFile arquivo
    ) {

        Familia familiaAtualizada = familiaService.atualizar(idFamilia, requestDto, arquivo);

        FamiliaResponseDto responseDto = FamiliaMapper.toResponse(familiaAtualizada);

        return ResponseEntity.ok(responseDto);
    }

    @Operation(
            summary = "Deletar família",
            description = "Deleta uma família, seu endereço e seus integrantes pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Família deletada com sucesso pelo ID"),
            @ApiResponse(responseCode = "404", description = "Família não encontrada pelo ID")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('excluir_familias')")
    public ResponseEntity<Void> deletarFamilia(@PathVariable Integer id) {

        familiaService.deletar(id);

        return ResponseEntity.status(204).build();
    }
}

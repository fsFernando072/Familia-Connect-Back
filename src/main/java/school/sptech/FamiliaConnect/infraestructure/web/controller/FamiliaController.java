package school.sptech.FamiliaConnect.infraestructure.web.controller;

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
import school.sptech.FamiliaConnect.application.ports.in.CarteirinhaUseCase;
import school.sptech.FamiliaConnect.application.ports.in.FamiliaUseCase;
import school.sptech.FamiliaConnect.domain.entity.carteirinha.ProcessamentoCarteirinha;
import school.sptech.FamiliaConnect.domain.entity.carteirinha.StatusCarteirinha;
import school.sptech.FamiliaConnect.infraestructure.web.dto.carteirinha.ProcessamentoCarteirinhaResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.carteirinha.StatusCarteirinhaResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.familia.*;
import school.sptech.FamiliaConnect.infraestructure.web.mapper.FamiliaCarteirinhaMapper;
import school.sptech.FamiliaConnect.infraestructure.web.mapper.FamiliaMapper;
import school.sptech.FamiliaConnect.domain.entity.Familia;
import school.sptech.FamiliaConnect.domain.entity.Pessoa;

import java.util.List;

@Tag(name = "Famílias", description = "Operações relacionadas às famílias")
@RestController
@RequestMapping("/familias")
public class FamiliaController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final FamiliaUseCase familiaUseCase;
    private final CarteirinhaUseCase carteirinhaUseCase;
    private FamiliaCarteirinhaMapper familiaCarteirinhaMapper;

    // Construtores ----------------------------------------------------------------------------------------------------

    public FamiliaController(FamiliaUseCase familiaUseCase, CarteirinhaUseCase carteirinhaUseCase, FamiliaCarteirinhaMapper familiaCarteirinhaMapper) {
        this.familiaUseCase = familiaUseCase;
        this.carteirinhaUseCase = carteirinhaUseCase;
        this.familiaCarteirinhaMapper = familiaCarteirinhaMapper;
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
    public ResponseEntity<ProcessamentoCarteirinhaResponseDto> cadastrarFamilia(
            @RequestPart("familiaRequestDto") @Valid FamiliaRequestDto familiaRequestDto,
            @RequestPart(value = "arquivo", required = false) MultipartFile arquivo
    ){

        ProcessamentoCarteirinha familiaCadastrada = familiaUseCase.salvar(familiaRequestDto, arquivo);

        ProcessamentoCarteirinhaResponseDto responseDto = familiaCarteirinhaMapper.toResponse(familiaCadastrada);

        return ResponseEntity.status(201).body(responseDto);

    }

    @Operation(
            summary = "Listar famílias",
            description = "Retorna uma lista paginada com as famílias cadastradas no sistema, " +
                    "com pesquisa opcional pelo nome do responsável (case insensitive) e ordenação opcional pelo nome do responsável"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de famílias retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de famílias retornada vazia")
    })
    @GetMapping
    @PreAuthorize("hasAuthority('listar_familias')")
    public ResponseEntity<Page<FamiliaListResponseDto>> listarFamilias(
            @RequestParam(required = false) String nomeResponsavel,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "asc") String direcao
    ){
        Sort.Direction direcaoOrdenacao = Sort.Direction.fromString(direcao);
        Sort ordenacao = Sort.by(direcaoOrdenacao, "nomeResponsavel", "idFamilia");
        PageRequest pageRequest = PageRequest.of(page, size, ordenacao);

        Page<FamiliaListResponseDto> familias = familiaUseCase.listar(nomeResponsavel, pageRequest);

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

        Familia familia = familiaUseCase.listarPorId(id);
        List<Pessoa> integrantes = familiaUseCase.listarIntegrantes(id);

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

        Familia familiaAtualizada = familiaUseCase.atualizar(idFamilia, requestDto, arquivo);

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

        familiaUseCase.deletar(id);

        return ResponseEntity.status(204).build();
    }


    @GetMapping("/responsaveis/{cpf}")
    @PreAuthorize("hasAuthority('cadastrar_entregas')")
    public ResponseEntity<Integer> buscarIdFamiliaPorCpfResponsavel(@PathVariable String cpf) {
        return ResponseEntity.ok(familiaUseCase.searchByCpfResponsavel(cpf));
    }


    @Operation(
            summary = "Criar carteirinha da família",
            description = "Solicita a geração da carteirinha da família informada (processamento assíncrono). " +
                    "Retorna os dados do processamento, incluindo o correlationId para consulta do status"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação de criação da carteirinha enviada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Família não encontrada pelo ID")
    })
    @PostMapping("/{id}/carteirinhas/")
    @PreAuthorize("hasAuthority('cadastrar_familias')")
    public ResponseEntity<ProcessamentoCarteirinhaResponseDto> criarCarteirinha(@PathVariable("id") Integer idFamilia) {

        ProcessamentoCarteirinha familiaCadastrada = carteirinhaUseCase.criarCarteirinha(null, idFamilia);

        ProcessamentoCarteirinhaResponseDto responseDto = familiaCarteirinhaMapper.toResponse(familiaCadastrada);

        return ResponseEntity.ok(responseDto);
    }

    @Operation(
            summary = "Buscar carteirinha da família",
            description = "Verifica se a família informada possui carteirinha cadastrada e retorna o ID da carteirinha"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carteirinha encontrada"),
            @ApiResponse(responseCode = "404", description = "Família sem carteirinha ou não encontrada")
    })
    @GetMapping("/{id}/carteirinhas")
    @PreAuthorize("hasAuthority('listar_familias')")
    public ResponseEntity<String> buscarCarteirinhaPorIdFamilia(@PathVariable("id") Integer idFamilia) {
        String idCarteirinha = carteirinhaUseCase.buscarCarteirinhaPorIdFamilia(idFamilia);
        return ResponseEntity.ok(idCarteirinha);
    }

    @Operation(
            summary = "Buscar status da carteirinha",
            description = "Retorna o status do processamento da carteirinha conforme o correlationId informado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status da carteirinha retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Status não encontrado para o correlationId informado")
    })
    @GetMapping("/buscar-status-carteirinha/{correlationId}")
    public ResponseEntity<StatusCarteirinhaResponseDto> buscarStatusCarteirinha(@PathVariable String correlationId) {
        StatusCarteirinha statusCarteirinha = carteirinhaUseCase.buscarStatusCarteirinha(correlationId);
        return ResponseEntity.ok(familiaCarteirinhaMapper.toResponse(statusCarteirinha));
    }

    // =========== PARA QR-CODE =========== //
    @Operation(
            summary = "Buscar família pela carteirinha",
            description = "Retorna o ID da família a partir do ID da carteirinha (utilizado na leitura do QR Code)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ID da família retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Família com a carteirinha informada não encontrada")
    })
    @GetMapping("/carteirinhas/{idCarteirinha}")
    public ResponseEntity<Integer> buscarIdCarteirinha(@PathVariable("idCarteirinha") String idCarteirinha) {
        Integer idFamilia = carteirinhaUseCase.searchByCarteirinha(idCarteirinha);
        return ResponseEntity.ok(idFamilia);
    }

}

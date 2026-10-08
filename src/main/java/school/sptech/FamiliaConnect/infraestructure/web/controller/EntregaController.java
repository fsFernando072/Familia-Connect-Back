package school.sptech.FamiliaConnect.infraestructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.FamiliaConnect.application.ports.in.EntregaUseCase;
import school.sptech.FamiliaConnect.infraestructure.web.dto.entrega.EntregaHistoricoResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.entrega.EntregaLoteRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.entrega.EntregaRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.entrega.FamiliaPendenteEntregaResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.entrega.EntregaResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.mapper.EntregaMapper;
import school.sptech.FamiliaConnect.domain.entity.Entrega;
import school.sptech.FamiliaConnect.application.service.EntregaService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Entregas", description = "Operações relacionadas às entregas realizadas às famílias")
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final EntregaUseCase entregaUseCase;

    // Construtores ----------------------------------------------------------------------------------------------------

    public EntregaController(EntregaUseCase entregaUseCase) {
        this.entregaUseCase = entregaUseCase;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Listar entregas",
            description = "Retorna uma lista com as entregas realizadas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de entregas retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de entregas retornada vazia")
    })
    @GetMapping
    @PreAuthorize("hasAuthority('listar_entregas')")
    public ResponseEntity<List<EntregaResponseDto>> listarEntregas(){

        List<Entrega> entregas = entregaUseCase.listar();

        if(entregas.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(EntregaMapper.toResponse(entregas));

    }

    @Operation(
            summary = "Cadastrar entrega completa de uma família",
            description = "Registra de uma só vez todos os produtos entregues a uma família. Tudo ou nada: se alguma regra falhar, nada é salvo"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Entrega cadastrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado (Pessoa, Funcionário, Produto ou estoque do mês não cadastrado)"),
            @ApiResponse(responseCode = "409", description = "Regra de negócio violada (limite de estoque do mês ou família já atendida no mês)")
    })
    @PostMapping("/lote")
    @PreAuthorize("hasAuthority('cadastrar_entregas')")
    public ResponseEntity<List<EntregaResponseDto>> cadastrarEntregaEmLote(@RequestBody @Valid EntregaLoteRequestDto requestDto){

        // Se o mesmo produto vier repetido, soma as quantidades.
        Map<Integer, Integer> quantidadesPorProduto = new LinkedHashMap<>();
        for (EntregaLoteRequestDto.Item item : requestDto.getItens()) {
            quantidadesPorProduto.merge(item.getIdProduto(), item.getQuantidade(), Integer::sum);
        }

        List<Entrega> entregas = entregaUseCase.salvarLote(
                requestDto.getIdPessoa(),
                requestDto.getIdFuncionario(),
                LocalDate.parse(requestDto.getDataEntrega()),
                quantidadesPorProduto
        );

        return ResponseEntity.status(201).body(EntregaMapper.toResponse(entregas));

    }

    @Operation(
            summary = "Listar famílias pendentes de entrega",
            description = "Retorna, de forma paginada, as famílias que não receberam entrega no mês informado (padrão: mês atual)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de famílias retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de famílias retornada vazia")
    })
    @GetMapping("/familias-pendentes")
    @PreAuthorize("hasAuthority('cadastrar_entregas')")
    public ResponseEntity<Page<FamiliaPendenteEntregaResponseDto>> listarFamiliasPendentes(
            @RequestParam(required = false) String nomeResponsavel,
            @RequestParam(required = false) String mes,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "asc") String direcao
    ){

        Sort ordenacao = Sort.by(Sort.Direction.fromString(direcao), "nomeResponsavel", "idFamilia");
        PageRequest pageRequest = PageRequest.of(page, size, ordenacao);

        Page<FamiliaPendenteEntregaResponseDto> familias = entregaUseCase.listarFamiliasPendentes(nomeResponsavel, lerMes(mes), pageRequest);

        if (familias.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(familias);

    }

    @Operation(
            summary = "Listar histórico de entregas",
            description = "Retorna, de forma paginada, uma linha por família e mês, com todos os produtos recebidos. Aceita filtro por mês (AAAA-MM)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Histórico de entregas retornado com sucesso"),
            @ApiResponse(responseCode = "204", description = "Histórico de entregas retornado vazio")
    })
    @GetMapping("/historico")
    @PreAuthorize("hasAuthority('listar_entregas')")
    public ResponseEntity<Page<EntregaHistoricoResponseDto>> listarHistorico(
            @RequestParam(required = false) String nomeResponsavel,
            @RequestParam(required = false) String mes,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "desc") String direcao
    ){

        Sort ordenacao = Sort.by(Sort.Direction.fromString(direcao), "dataDaEntrega", "idEntrega");
        PageRequest pageRequest = PageRequest.of(page, size, ordenacao);

        Page<EntregaHistoricoResponseDto> entregas = entregaUseCase.listarHistorico(nomeResponsavel, lerMes(mes), pageRequest);

        if (entregas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(entregas);

    }

    @Operation(
            summary = "Listar entrega",
            description = "Retorna uma entrega realizada pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Entrega não encontrada pelo ID"),
            @ApiResponse(responseCode = "200", description = "Entrega retornada com sucesso pelo ID")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('listar_entregas')")
    public ResponseEntity<EntregaResponseDto> listarPorId(@PathVariable Integer id){

        Entrega entrega = entregaUseCase.listarPorId(id);

        return ResponseEntity.status(200).body(EntregaMapper.toResponse(entrega));

    }

    @Operation(
            summary = "Cadastrar entrega",
            description = "Cadastra uma entrega pelos dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Entrega cadastrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado (Pessoa, Funcionário, Produto ou estoque do mês não cadastrado)"),
            @ApiResponse(responseCode = "409", description = "Regra de negócio violada (limite de estoque do mês ou família já atendida no mês)")
    })
    @PostMapping
    @PreAuthorize("hasAuthority('cadastrar_entregas')")
    public ResponseEntity<EntregaResponseDto> cadastrarEntrega(@RequestBody @Valid EntregaRequestDto requestDto){

        Entrega entrega = entregaUseCase.salvar(EntregaMapper.toModel(requestDto));

        return ResponseEntity.status(201).body(EntregaMapper.toResponse(entrega));

    }

    @Operation(
            summary = "Atualizar entrega",
            description = "Atualiza uma entrega pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrega atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Entrega ou recurso relacionado não encontrado"),
            @ApiResponse(responseCode = "409", description = "Regra de negócio violada (limite de estoque do mês ou família já atendida no mês)")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('editar_entregas')")
    public ResponseEntity<EntregaResponseDto> atualizarEntrega(
            @PathVariable Integer id,
            @RequestBody @Valid EntregaRequestDto requestDto
    ){

        Entrega entrega = entregaUseCase.atualizar(id, EntregaMapper.toModel(requestDto));

        return ResponseEntity.status(200).body(EntregaMapper.toResponse(entrega));

    }

    @Operation(
            summary = "Deletar entrega",
            description = "Deleta uma entrega pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Entrega deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Entrega não encontrada pelo ID")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('excluir_entregas')")
    public ResponseEntity<Void> deletarEntrega(@PathVariable Integer id){

        entregaUseCase.deletar(id);

        return ResponseEntity.status(204).build();

    }

    @Operation(
            summary = "Deletar entrega de uma família no mês",
            description = "Deleta todas as entregas (produtos) de uma família no mês informado. Se for o mês atual, a família volta a ficar pendente de entrega"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Entrega deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Mês inválido (use AAAA-MM)"),
            @ApiResponse(responseCode = "404", description = "Nenhuma entrega da família no mês informado")
    })
    @DeleteMapping("/familia/{idFamilia}")
    @PreAuthorize("hasAuthority('excluir_entregas')")
    public ResponseEntity<Void> deletarEntregaDaFamilia(@PathVariable Integer idFamilia, @RequestParam String mes){

        YearMonth mesEntrega = lerMes(mes);

        if (mesEntrega == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe o mês no formato AAAA-MM");
        }

        entregaUseCase.deletarPorFamiliaNoMes(idFamilia, mesEntrega);

        return ResponseEntity.status(204).build();

    }

    // Converte "AAAA-MM" em YearMonth. Vazio/ausente vira null (sem filtro); formato inválido vira 400.
    private YearMonth lerMes(String mes){

        if (mes == null || mes.isBlank()) {
            return null;
        }

        try {
            return YearMonth.parse(mes.trim());
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mês inválido, use o formato AAAA-MM");
        }
    }

}

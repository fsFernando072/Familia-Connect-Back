package school.sptech.FamiliaConnect.infraestructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.application.ports.in.FuncionarioUseCase;
import school.sptech.FamiliaConnect.infraestructure.web.dto.funcionario.*;
import school.sptech.FamiliaConnect.infraestructure.web.mapper.FuncionarioMapper;
import school.sptech.FamiliaConnect.domain.entity.Funcionario;
import school.sptech.FamiliaConnect.application.service.FuncionarioService;

import java.time.Duration;

@Tag(name = "Funcionários", description = "Operações relacionadas aos funcionários")
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    public static final String COOKIE_NOME = "authToken";

    @Value("${jwt.validity}")
    private long jwtValidity;

    private final FuncionarioUseCase funcionarioUseCase;

    // Construtores ----------------------------------------------------------------------------------------------------

    public FuncionarioController(FuncionarioUseCase funcionarioUseCase) {
        this.funcionarioUseCase = funcionarioUseCase;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Listar funcionários",
            description = "Retorna uma lista paginada dos funcionários cadastrados no sistema, " +
                    "com pesquisa opcional pelo nome (case insensitive) e ordenação opcional pelo nome"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de funcionários retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de funcionários retornada vazia")
    })
    @GetMapping
    @PreAuthorize("hasAuthority('listar_funcionarios')")
    public ResponseEntity<Page<FuncionarioResponseDto>> listarFuncionarios(
            @RequestParam(required = false) String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "asc") String direcao
    ){

        Sort.Direction direcaoOrdenacao = Sort.Direction.fromString(direcao);
        Sort ordenacao = Sort.by(direcaoOrdenacao, "nome", "id");
        Pageable pageable = PageRequest.of(page, size, ordenacao);

        Page<Funcionario> funcionarios = funcionarioUseCase.listar(nome, pageable);

        if(funcionarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(funcionarios.map(FuncionarioMapper::toResponse));
    }

    @Operation(
            summary = "Listar funcionário",
            description = "Retorna um funcionário pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário retornado com sucesso pelo ID"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado pelo ID")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('listar_funcionarios')")
    public ResponseEntity<FuncionarioResponseDto> listarPorId(@PathVariable Integer id){

        Funcionario funcionario = funcionarioUseCase.listarPorId(id);

        return ResponseEntity.status(200).body(FuncionarioMapper.toResponse(funcionario));

    }

    @Operation(
            summary = "Cadastrar funcionário",
            description = "Cadastra um funcionário pelos dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário cadastrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cargo não encontrado pelo ID")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('cadastrar_funcionarios')")
    public ResponseEntity<FuncionarioResponseDto> cadastrarFuncionario(
            @RequestPart("funcionarioRequestDto") @Valid FuncionarioRequestDto requestDto,
            @RequestPart(value = "arquivo", required = false) MultipartFile arquivo
    ){

        Funcionario funcionarioCadastrado = funcionarioUseCase.salvar(FuncionarioMapper.toModel(requestDto), arquivo);

        return ResponseEntity.status(201).body(FuncionarioMapper.toResponse(funcionarioCadastrado));

    }

    @Operation(
            summary = "Atualizar funcionário",
            description = "Atualiza um funcionário com os dados fornecidos pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado pelo ID")
    })
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('editar_funcionarios')")
    public ResponseEntity<FuncionarioResponseDto> atualizarFuncionario(
            @PathVariable Integer id,
            @RequestPart(("funcionarioRequestDto")) @Valid FuncionarioRequestDto requestDto,
            @RequestPart(value = "arquivo", required = false) MultipartFile arquivo
    ){

        Funcionario funcionarioAtualizado = funcionarioUseCase.atualizar(id, FuncionarioMapper.toModel(requestDto), arquivo);

        return ResponseEntity.status(200).body(FuncionarioMapper.toResponse(funcionarioAtualizado));

    }
    @Operation(
            summary = "Deletar funcionário",
            description = "Deleta um funcionário pelo ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Funcionário deletado com sucesso pelo ID"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado pelo ID")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('excluir_funcionarios')")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Integer id){

        funcionarioUseCase.deletar(id);

        return ResponseEntity.status(204).build();

    }

    @PostMapping("/login")
    public ResponseEntity<FuncionarioSessaoDto> login(
            @Valid @RequestBody FuncionarioLoginDto usuarioLoginDto,
            HttpServletResponse response) {

        final Funcionario usuario = FuncionarioMapper.of(usuarioLoginDto);

        // autenticar() gera o token internamente — precisamos dele apenas para o cookie
        FuncionarioTokenDto autenticado = this.funcionarioUseCase.autenticar(usuario);

        // Token vai para o cookie HttpOnly — inacessível ao JavaScript (proteção XSS)
        ResponseCookie cookie = ResponseCookie.from(COOKIE_NOME, autenticado.getToken())
                .httpOnly(true)                          // inacessível ao JavaScript
                .secure(false)                           // true em produção (exige HTTPS)
                .sameSite("Strict")                      // bloqueia envio cross-site (mitiga CSRF)
                .path("/")                               // valido para toda a aplicacao
                .maxAge(Duration.ofSeconds(jwtValidity)) // expira junto com o token JWT
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        // Body retorna apenas dados de sessão — sem o token
        FuncionarioSessaoDto sessao = FuncionarioMapper.ofSessao(autenticado);
        return ResponseEntity.ok(sessao);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from(COOKIE_NOME, "")
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(0)  // maxAge=0 instrui o browser a deletar o cookie imediatamente
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.noContent().build();
    }
}

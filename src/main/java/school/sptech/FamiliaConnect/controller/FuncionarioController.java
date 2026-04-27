package school.sptech.FamiliaConnect.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.FamiliaConnect.dto.funcionario.*;
import school.sptech.FamiliaConnect.mapper.FuncionarioMapper;
import school.sptech.FamiliaConnect.model.Funcionario;
import school.sptech.FamiliaConnect.service.FuncionarioService;

import java.time.Duration;
import java.util.List;

@Tag(name = "Funcionários", description = "Operações relacionadas aos funcionários")
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    public static final String COOKIE_NOME = "authToken";

    @Value("${jwt.validity}")
    private long jwtValidity;

    private final FuncionarioService funcionarioService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    // Endpoints -------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Listar funcionários",
            description = "Retorna uma lista de todos os funcionários cadastrados no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de funcionários retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Lista de funcionários retornada vazia")
    })
    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDto>> listarFuncionarios(){

        List<Funcionario> funcionarios = funcionarioService.listar();

        if(funcionarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(FuncionarioMapper.toResponse(funcionarios));

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
    public ResponseEntity<FuncionarioResponseDto> listarPorId(@PathVariable Integer id){

        Funcionario funcionario = funcionarioService.listarPorId(id);

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
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<FuncionarioResponseDto> cadastrarFuncionario(@RequestBody @Valid FuncionarioRequestDto requestDto){

        Funcionario funcionarioCadastrado = funcionarioService.salvar(requestDto);

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
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDto> atualiazarFuncionario(@RequestBody @Valid FuncionarioRequestDto requestDto, @PathVariable Integer id){

        Funcionario funcionarioAtualizado = funcionarioService.atualizar(requestDto, id);

        return ResponseEntity.status(200).body(FuncionarioMapper.toResponse(funcionarioAtualizado));

    }

    @PostMapping("/login")
    public ResponseEntity<FuncionarioSessaoDto> login(
            @RequestBody FuncionarioLoginDto usuarioLoginDto,
            HttpServletResponse response) {

        final Funcionario usuario = FuncionarioMapper.of(usuarioLoginDto);

        // autenticar() gera o token internamente — precisamos dele apenas para o cookie
        FuncionarioTokenDto autenticado = this.funcionarioService.autenticar(usuario);

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

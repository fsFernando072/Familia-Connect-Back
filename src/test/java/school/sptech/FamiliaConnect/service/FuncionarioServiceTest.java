package school.sptech.FamiliaConnect.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.FamiliaConnect.config.GerenciadorTokenJwt;
import school.sptech.FamiliaConnect.dto.funcionario.FuncionarioTokenDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.model.Funcionario;
import school.sptech.FamiliaConnect.repository.CargoRepository;
import school.sptech.FamiliaConnect.repository.FuncionarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FuncionarioServiceTest {

    @Mock
    FuncionarioRepository funcionarioRepository;

    @Mock
    CargoRepository cargoRepository;

    @Mock
    GerenciadorTokenJwt gerenciadorTokenJwt;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    FuncionarioService funcionarioService;

    @Nested
    @DisplayName("Deve retornar listas dos funcionários corretamente")
    class listar {

        @Test
        @DisplayName("Deve retornar lista com todos os funcionários")
        void retornarListaComTodosFuncionarios() {
            List<Funcionario> funcionarios = new ArrayList<>();

            Funcionario funcionario = new Funcionario("João Silva", "123.456.789-00", "senha123", "foto.png");
            funcionario.setId(1);
            funcionarios.add(funcionario);

            Mockito.when(funcionarioRepository.findAll())
                    .thenReturn(funcionarios);

            List<Funcionario> resultado = funcionarioService.listar();

            Assertions.assertIterableEquals(funcionarios, resultado);
        }

        @Test
        @DisplayName("Deve retornar lista vazia caso não exista nenhum funcionário")
        void retornarListaVazia() {
            List<Funcionario> funcionarios = new ArrayList<>();

            Mockito.when(funcionarioRepository.findAll())
                    .thenReturn(funcionarios);

            List<Funcionario> resultado = funcionarioService.listar();

            Assertions.assertTrue(resultado.isEmpty());
        }
    }

    @Nested
    @DisplayName("Deve buscar os funcionários corretamente")
    class listarPorId {

        @Test
        @DisplayName("Deve retornar um funcionário que existe através do seu ID")
        void retornarFuncionarioPorId() {
            Funcionario funcionario = new Funcionario("João Silva", "123.456.789-00", "senha123", "foto.png");
            funcionario.setId(1);

            Mockito.when(funcionarioRepository.findById(1))
                    .thenReturn(Optional.of(funcionario));

            Funcionario resultado = funcionarioService.listarPorId(1);

            Assertions.assertEquals(1, resultado.getId());
            Assertions.assertEquals("João Silva", resultado.getNome());
        }

        @Test
        @DisplayName("Deve lançar EntidadeNaoEncontradaException quando funcionário não for encontrado pelo ID")
        void retornarExceptionFuncionarioNaoEncontrado() {
            Integer id = 99;

            Mockito.when(funcionarioRepository.findById(id))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> funcionarioService.listarPorId(id)
            );
        }
    }

    @Nested
    @DisplayName("Deve salvar os funcionários corretamente")
    class salvar {

        @Test
        @DisplayName("Deve salvar funcionário com sucesso")
        void salvarFuncionario() {
            Cargo cargo = new Cargo();
            cargo.setId(1);
            cargo.setNome("Administrador");

            Funcionario funcionario = new Funcionario("Maria Souza", "123.456.789-00", "senha123", "foto.png");
            funcionario.setCargo(cargo);

            Funcionario funcionarioSalvo = new Funcionario("Maria Souza", "123.456.789-00", "senhaHash", "foto.png");
            funcionarioSalvo.setId(1);
            funcionarioSalvo.setCargo(cargo);

            Mockito.when(cargoRepository.findById(1))
                    .thenReturn(Optional.of(cargo));

            Mockito.when(passwordEncoder.encode(Mockito.anyString()))
                    .thenReturn("senhaHash");

            Mockito.when(funcionarioRepository.save(Mockito.any(Funcionario.class)))
                    .thenReturn(funcionarioSalvo);

            Funcionario resultado = funcionarioService.salvar(funcionario);

            Assertions.assertEquals(1, resultado.getId());
            Assertions.assertEquals("Maria Souza", resultado.getNome());
            Assertions.assertNotNull(resultado.getCargo());
        }

        @Test
        @DisplayName("Deve lançar EntidadeNaoEncontradaException quando cargo não for encontrado")
        void salvarFuncionarioCargoNaoEncontrado() {
            Cargo cargo = new Cargo();
            cargo.setId(99);

            Funcionario funcionario = new Funcionario("Maria Souza", "123.456.789-00", "senha123", "foto.png");
            funcionario.setCargo(cargo);

            Mockito.when(cargoRepository.findById(99))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> funcionarioService.salvar(funcionario)
            );
        }
    }

    @Nested
    @DisplayName("Deve atualizar os funcionários corretamente")
    class atualizar {

        @Test
        @DisplayName("Deve atualizar funcionário com sucesso")
        void atualizarFuncionario() {
            Integer id = 1;

            Cargo cargo = new Cargo();
            cargo.setId(1);
            cargo.setNome("Administrador");

            Funcionario funcionario = new Funcionario("João Atualizado", "123.456.789-00", "novaSenha", "novaFoto.png");
            funcionario.setCargo(cargo);

            Funcionario funcionarioAtualizado = new Funcionario("João Atualizado", "123.456.789-00", "novaSenha", "novaFoto.png");
            funcionarioAtualizado.setId(id);
            funcionarioAtualizado.setCargo(cargo);

            Mockito.when(cargoRepository.findById(1))
                    .thenReturn(Optional.of(cargo));

            Mockito.when(funcionarioRepository.existsById(id))
                    .thenReturn(true);

            Mockito.when(funcionarioRepository.save(Mockito.any(Funcionario.class)))
                    .thenReturn(funcionarioAtualizado);

            Funcionario resultado = funcionarioService.atualizar(funcionario, id);

            Assertions.assertEquals(id, resultado.getId());
            Assertions.assertEquals("João Atualizado", resultado.getNome());
        }

        @Test
        @DisplayName("Deve lançar EntidadeNaoEncontradaException quando cargo não for encontrado ao atualizar")
        void atualizarFuncionarioCargoNaoEncontrado() {
            Integer id = 1;

            Cargo cargo = new Cargo();
            cargo.setId(1);

            Funcionario funcionario = new Funcionario("João", "123.456.789-00", "senha", "foto.png");
            funcionario.setCargo(cargo);

            Mockito.when(cargoRepository.findById(1))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> funcionarioService.atualizar(funcionario, id)
            );
        }

        @Test
        @DisplayName("Deve lançar EntidadeNaoEncontradaException quando funcionário não existir ao atualizar")
        void atualizarFuncionarioNaoEncontrado() {
            Integer id = 99;

            Cargo cargo = new Cargo();
            cargo.setId(1);

            Funcionario funcionario = new Funcionario("João", "123.456.789-00", "senha", "foto.png");
            funcionario.setCargo(cargo);

            Mockito.when(cargoRepository.findById(1))
                    .thenReturn(Optional.of(cargo));

            Mockito.when(funcionarioRepository.existsById(id))
                    .thenReturn(false);

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> funcionarioService.atualizar(funcionario, id)
            );
        }
    }

    @Nested
    @DisplayName("Deve autenticar os funcionários corretamente")
    class autenticar {

        @Test
        @DisplayName("Deve autenticar funcionário com sucesso e retornar token")
        void autenticarFuncionarioComSucesso() {
            Funcionario usuario = new Funcionario("123.456.789-00", "senha123");

            Authentication authentication = Mockito.mock(Authentication.class);

            Cargo cargo = new Cargo();
            cargo.setId(1);
            cargo.setNome("Admin");

            Funcionario usuarioAutenticado = new Funcionario("João", "123.456.789-00", "senhaHash", "foto.png");
            usuarioAutenticado.setId(1);
            usuarioAutenticado.setCargo(cargo);

            Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                    .thenReturn(authentication);

            Mockito.when(funcionarioRepository.findByCpf("123.456.789-00"))
                    .thenReturn(Optional.of(usuarioAutenticado));

            Mockito.when(gerenciadorTokenJwt.generateToken(authentication))
                    .thenReturn("token-mock");

            FuncionarioTokenDto resultado = funcionarioService.autenticar(usuario);

            Assertions.assertEquals("123.456.789-00", resultado.getCpf());
            Assertions.assertEquals("token-mock", resultado.getToken());
        }

        @Test
        @DisplayName("Deve lançar exceção quando CPF não estiver cadastrado")
        void autenticarFuncionarioCpfNaoEncontrado() {
            Funcionario usuario = new Funcionario("999.999.999-99", "senha123");

            Authentication authentication = Mockito.mock(Authentication.class);

            Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                    .thenReturn(authentication);

            Mockito.when(funcionarioRepository.findByCpf("999.999.999-99"))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(
                    Exception.class,
                    () -> funcionarioService.autenticar(usuario)
            );
        }
    }
}





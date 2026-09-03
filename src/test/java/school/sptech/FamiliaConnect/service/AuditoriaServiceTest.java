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
import school.sptech.FamiliaConnect.application.service.AuditoriaService;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.domain.entity.Auditoria;
import school.sptech.FamiliaConnect.domain.entity.Cargo;
import school.sptech.FamiliaConnect.domain.entity.Funcionario;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.AuditoriaRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.FuncionarioRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AuditoriaServiceTest {

    @Mock
    AuditoriaRepository auditoriaRepository;
    @Mock
    FuncionarioRepository funcionarioRepository;

    @InjectMocks
    AuditoriaService auditoriaService;

    @Nested
    @DisplayName("Deve cadastrar as auditorias corretamente")
    class cadastrar {
        @Test
        @DisplayName("Cadastrar auditoria corretamente")
        void cadastrarAuditoria(){

            Cargo cargo = new Cargo();
            cargo.setNome("Analista");
            cargo.setId(1);

            Funcionario funcionario = new Funcionario();
            funcionario.setId(1);
            funcionario.setCargo(cargo);
            funcionario.setSenha("123456");
            funcionario.setCpf("330.983.740-80");
            funcionario.setNome("Pedro");

            Auditoria auditoria = new Auditoria();

            auditoria.setFuncionario(funcionario);
            auditoria.setCreatedAt(LocalDate.of(2026, 5, 17));
            auditoria.setDadoAntigo("DadoAntigo");
            auditoria.setDadoNovo("DadoNovo");
            auditoria.setTipoLog("Log");

            Auditoria auditoriaMock = auditoria;
            auditoriaMock.setFuncionario(funcionario);

            Mockito.when(funcionarioRepository.findById(1))
                    .thenReturn(Optional.of(funcionario));

            Mockito.when(auditoriaRepository.save(Mockito.any(Auditoria.class)))
                    .thenReturn(auditoriaMock);

            Auditoria resultado = auditoriaService.cadastrar(auditoria);

            Assertions.assertEquals( funcionario.getId(), resultado.getFuncionario().getId());
            Assertions.assertEquals(auditoria.getCreatedAt(), resultado.getCreatedAt());
            Assertions.assertEquals(auditoria.getDadoAntigo(), resultado.getDadoAntigo());
            Assertions.assertEquals(auditoria.getDadoNovo(), resultado.getDadoNovo());
            Assertions.assertEquals(auditoria.getTipoLog(), resultado.getTipoLog());
        }


    }

    @Nested
    @DisplayName("Deve retornar listas das auditorias corretamente")
    class listar {
        @Test
        @DisplayName("Retoranar uma lista com todas auditorias")
        void retornarListaComTodasAuditorias(){

            Cargo cargo = new Cargo();
            cargo.setNome("Analista");
            cargo.setId(1);

            Funcionario funcionario = new Funcionario();
            funcionario.setId(1);
            funcionario.setCargo(cargo);
            funcionario.setSenha("123456");
            funcionario.setCpf("330.983.740-80");
            funcionario.setNome("Pedro");
            List<Auditoria> auditorias = new ArrayList<>();

            Auditoria auditoria = new Auditoria();
            auditoria.setFuncionario(funcionario);
            auditoria.setId(1);
            auditoria.setCreatedAt(LocalDate.of(2026, 5, 17));
            auditoria.setDadoNovo("DadoNovo");
            auditoria.setDadoAntigo("DadoAntigo");
            auditoria.setTipoLog("Log");


            auditorias.add(auditoria);

            Mockito.when(auditoriaRepository.findAll())
                    .thenReturn(auditorias);
            List<Auditoria> resultado = auditoriaService.listar();

            Assertions.assertIterableEquals(auditorias, resultado);
        }
        @Test
        @DisplayName("Retoranar uma lista vazia caso não exista nenhuma permissão de auditoria")
        void retornarListaVazia(){
            List<Auditoria> auditorias = new ArrayList<>();

            Mockito.when(auditoriaRepository.findAll())
                    .thenReturn(auditorias);
            List<Auditoria> resultado = auditoriaService.listar();

            Assertions.assertIterableEquals(auditorias, resultado);
        }
    }

    @Nested
    @DisplayName("Deve buscar as auditorias corretamente")
    class buscarPorId {
        @Test
        @DisplayName("Deve retornar um auditoria que existe através do seu ID")
        void retornarAuditoriaPorId(){
            Cargo cargo = new Cargo();
            cargo.setNome("Analista");
            cargo.setId(1);

            Funcionario funcionario = new Funcionario();
            funcionario.setId(1);
            funcionario.setCargo(cargo);
            funcionario.setSenha("123456");
            funcionario.setCpf("330.983.740-80");
            funcionario.setNome("Pedro");

            Auditoria auditoria = new Auditoria();
            auditoria.setId(1);
            auditoria.setFuncionario(funcionario);
            auditoria.setId(1);
            auditoria.setCreatedAt(LocalDate.of(2026, 5, 17));
            auditoria.setDadoNovo("DadoNovo");
            auditoria.setDadoAntigo("DadoAntigo");
            auditoria.setTipoLog("Log");

            Mockito.when(auditoriaRepository.findById(1))
                    .thenReturn(Optional.of(auditoria));
            Auditoria resultado = auditoriaService.buscarPorId(1);

            Assertions.assertEquals( auditoria.getFuncionario(), resultado.getFuncionario());
            Assertions.assertEquals(auditoria.getCreatedAt(), resultado.getCreatedAt());
            Assertions.assertEquals(auditoria.getDadoAntigo(), resultado.getDadoAntigo());
            Assertions.assertEquals(auditoria.getDadoNovo(), resultado.getDadoNovo());
            Assertions.assertEquals(auditoria.getTipoLog(), resultado.getTipoLog());


        }

        @Test
        @DisplayName("Deve EntidadeNaoEncontradaExceptio quando buscar por ID não encotrar nada")
        void retornarExceptionAuditoriaNaoEncontrado(){

            Integer id = 1;
            Optional<Auditoria> optional = Optional.empty();

            Mockito.when(auditoriaRepository.findById(id))
                    .thenReturn(optional);

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> auditoriaService.buscarPorId(id)
            );
        }
    }

    @Nested
    @DisplayName("Deve atualizar as auditorias corretamente")
    class atualizar {
        @Test
        @DisplayName("Deve atualizar um auditoria existente")
        void atualizarAuditoria() {

            Integer id = 1;

            Cargo cargo = new Cargo();
            cargo.setNome("Analista");
            cargo.setId(1);

            Funcionario funcionario = new Funcionario();
            funcionario.setId(1);
            funcionario.setCargo(cargo);
            funcionario.setSenha("123456");
            funcionario.setCpf("330.983.740-80");
            funcionario.setNome("Pedro");

            Auditoria auditoria = new Auditoria();
            auditoria.setTipoLog("UPDATE");
            auditoria.setDadoAntigo("DadoAntigo");
            auditoria.setDadoNovo("DadoNovo");
            auditoria.setCreatedAt(LocalDate.now());
            auditoria.setFuncionario(funcionario);

            Auditoria auditoriaAtualizada = auditoria;
            auditoriaAtualizada.setId(id);
            auditoriaAtualizada.setFuncionario(funcionario);

            Mockito.when(auditoriaRepository.existsById(id))
                    .thenReturn(true);

            Mockito.when(funcionarioRepository.findById(1))
                    .thenReturn(Optional.of(funcionario));

            Mockito.when(auditoriaRepository.save(Mockito.any(Auditoria.class)))
                    .thenReturn(auditoriaAtualizada);

            Auditoria resultado = auditoriaService.atualizar(id, auditoria);

            Assertions.assertEquals(id, resultado.getId());
            Assertions.assertEquals(auditoria.getTipoLog(), resultado.getTipoLog());
            Assertions.assertEquals(auditoria.getDadoAntigo(), resultado.getDadoAntigo());
            Assertions.assertEquals(auditoria.getDadoNovo(), resultado.getDadoNovo());
            Assertions.assertEquals(auditoria.getCreatedAt(), resultado.getCreatedAt());
            Assertions.assertEquals(funcionario, resultado.getFuncionario());
        }

    }

    @Nested
    @DisplayName("Deve deletar as auditorias corretamente")
    class deletar {
        @Test
        @DisplayName("Deve deletar um auditoria existente")
        void deletarAuditoria() {

            Integer id = 1;

            Mockito.when(auditoriaRepository.existsById(id))
                    .thenReturn(true);

            Assertions.assertDoesNotThrow(() -> auditoriaService.deletar(id));

            Mockito.verify(auditoriaRepository, Mockito.times(1))
                    .deleteById(id);
        }

        @Test
        @DisplayName("Deve retornar EntidadeNaoEncontradaException ao deletar auditoria inexistente")
        void deletarAuditoriaInexistente() {

            Integer id = 1;

            Mockito.when(auditoriaRepository.existsById(id))
                    .thenReturn(false);

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> auditoriaService.deletar(id)
            );
        }
    }
}
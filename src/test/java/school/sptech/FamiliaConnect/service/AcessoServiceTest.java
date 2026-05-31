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
import school.sptech.FamiliaConnect.dto.Acesso.AcessoRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.AcessoMapper;
import school.sptech.FamiliaConnect.model.Acesso;
import school.sptech.FamiliaConnect.repository.AcessoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AcessoServiceTest {

    @Mock
    AcessoRepository acessoRepository;



    @InjectMocks
    AcessoService acessoService;


    @Nested
    @DisplayName("Deve cadastrar os acessos corretamente")
    class cadastrar {
        @Test
        @DisplayName("Cadastrar acesso corretamente")
        void cadastrarAcesso(){
            Acesso acesso = new Acesso();

            acesso.setNomeTela("acesso1");



            Mockito.when(acessoRepository.save(Mockito.any(Acesso.class)))
                    .thenReturn(acesso);

            Acesso resultado = acessoService.cadastrar(acesso);

            Assertions.assertEquals(acesso.getId(), resultado.getId());
            Assertions.assertEquals(acesso.getNomeTela(), resultado.getNomeTela());
        }

        @Test
        @DisplayName("Deve retornar EntidadeJaCadastradaException")
        void acessoDuplicado(){

            String nome = "acesso1";

            Acesso acesso = new Acesso();

            acesso.setNomeTela("acesso1");

            //When
            Mockito.when(acessoRepository.findByNomeTela(nome))
                    .thenReturn(Optional.of(acesso));

            //then
            Assertions.assertThrows(
                    EntidadeJaCadastradaException.class,
                    () -> acessoService.cadastrar(acesso)
            );
        }


    }

    @Nested
    @DisplayName("Deve retornar listas dos acessos corretamente")
    class listar {
        @Test
        @DisplayName("Retoranar uma lista com todos acessos")
        void retornarListaComTodosAcessos(){
            List<Acesso> acessos = new ArrayList<>();

            Acesso acesso = new Acesso();

            acesso.setId(1);
            acesso.setNomeTela("acesso1");

            acessos.add(acesso);

            Mockito.when(acessoRepository.findAll())
                    .thenReturn(acessos);
            List<Acesso> resultado = acessoService.listar();

            Assertions.assertIterableEquals(acessos, resultado);
        }
        @Test
        @DisplayName("Retoranar uma lista vazia caso não exista nenhuma permissão de acesso")
        void retornarListaVazia(){
            List<Acesso> acessos = new ArrayList<>();

            Mockito.when(acessoRepository.findAll())
                    .thenReturn(acessos);
            List<Acesso> resultado = acessoService.listar();

            Assertions.assertIterableEquals(acessos, resultado);
        }
    }

    @Nested
    @DisplayName("Deve buscar os acessos corretamente")
    class buscar {
        @Test
        @DisplayName("Deve retornar um acesso que existe através do seu ID")
        void retornarAcessoPorId(){

            Acesso acesso = new Acesso();

            acesso.setId(1);
            acesso.setNomeTela("acesso1");


            Mockito.when(acessoRepository.findById(1))
                    .thenReturn(Optional.of(acesso));
            Acesso resultado = acessoService.buscarPorId(1);

            Assertions.assertEquals(1, resultado.getId());
            Assertions.assertEquals("acesso1", resultado.getNomeTela());


        }

        @Test
        @DisplayName("Deve EntidadeNaoEncontradaExceptio quando buscar por ID não encotrar nada")
        void retornarExceptionAcessoNaoEncontrado(){

            Integer id = 1;
            Optional<Acesso> optional = Optional.empty();

            //When
            Mockito.when(acessoRepository.findById(id))
                    .thenReturn(optional);

            //then
            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> acessoService.buscarPorId(id)
            );


        }

    }

    @Nested
    @DisplayName("Deve atualizar os acessos corretamente")
    class atualizar {
        @Test
        @DisplayName("Deve atualizar um acesso existente")
        void atualizarAcesso() {

            Integer id = 1;

            Acesso acesso = new Acesso();
            acesso.setNomeTela("acessoAtualizado");

            Acesso acessoAtualizado = acesso;
            acessoAtualizado.setId(id);

            Mockito.when(acessoRepository.existsById(id))
                    .thenReturn(true);

            Mockito.when(acessoRepository.save(Mockito.any(Acesso.class)))
                    .thenReturn(acessoAtualizado);

            Acesso resultado = acessoService.atualizar(id, acesso);

            Assertions.assertEquals(id, resultado.getId());
            Assertions.assertEquals("acessoAtualizado", resultado.getNomeTela());
        }

        @Test
        @DisplayName("Deve retornar EntidadeNaoEncontradaException ao atualizar acesso inexistente")
        void atualizarAcessoInexistente() {

            Integer id = 1;

            Acesso acesso = new Acesso();
            acesso.setNomeTela("acessoAtualizado");

            Mockito.when(acessoRepository.existsById(id))
                    .thenReturn(false);

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> acessoService.atualizar(id, acesso)
            );
        }
    }

@Nested
@DisplayName("Deve deletar os acessos corretamente")
class deletar {

    @Test
    @DisplayName("Deve deletar um acesso existente")
    void deletarAcesso() {

        Integer id = 1;

        Mockito.when(acessoRepository.existsById(id))
                .thenReturn(true);

        Assertions.assertDoesNotThrow(() -> acessoService.deletar(id));

        Mockito.verify(acessoRepository, Mockito.times(1))
                .deleteById(id);
    }

    @Test
    @DisplayName("Deve retornar EntidadeNaoEncontradaException ao deletar acesso inexistente")
    void deletarAcessoInexistente() {

        Integer id = 1;

        Mockito.when(acessoRepository.existsById(id))
                .thenReturn(false);

        Assertions.assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> acessoService.deletar(id)
            );
        }
    }
}

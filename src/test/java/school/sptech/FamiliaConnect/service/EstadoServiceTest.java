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
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.model.Estado;
import school.sptech.FamiliaConnect.repository.EstadoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class EstadoServiceTest {
    @Mock
    EstadoRepository estadoRepository;

    @InjectMocks
    EstadoService estadoService;

    @Nested
    @DisplayName("Deve listar todos estados corretamente")
    class listar{
        @Test
        @DisplayName("Retoranar uma lista com todos estados")
        void retornarListaComTodosEstados(){
            List<Estado> estados = new ArrayList<>();

            Estado estado = new Estado();

            estado.setId(1);
            estado.setNome("estado1");

            estados.add(estado);

            Mockito.when(estadoRepository.findAll())
                    .thenReturn(estados);
            List<Estado> resultado = estadoService.listar();

            Assertions.assertIterableEquals(estados, resultado);
        }
        @Test
        @DisplayName("Retoranar uma lista vazia caso não exista nenhum estado")
        void retornarListaVazia(){
            List<Estado> estados = new ArrayList<>();

            Mockito.when(estadoRepository.findAll())
                    .thenReturn(estados);
            List<Estado> resultado = estadoService.listar();

            Assertions.assertIterableEquals(estados, resultado);
        }
    }

    @Nested
    @DisplayName("Deve buscar o estado corretamente")
    class buscar {
        @Test
        @DisplayName("Deve retornar um estado que existe através do seu ID")
        void retornarEstadoPorId(){

            Estado estado = new Estado();

            estado.setId(1);
            estado.setNome("estado1");

            Mockito.when(estadoRepository.findById(1))
                    .thenReturn(Optional.of(estado));
            Estado resultado = estadoService.buscarPorId(1);

            Assertions.assertEquals(1, resultado.getId());
            Assertions.assertEquals("estado1", resultado.getNome());

        }

        @Test
        @DisplayName("Deve retornar EntidadeNaoEncontradaExceptio quando buscar por ID não encotrar nada")
        void retornarExceptionEstadoNaoEncontrado(){

            Integer id = 1;
            Optional<Estado> optional = Optional.empty();

            Mockito.when(estadoRepository.findById(id))
                    .thenReturn(optional);

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> estadoService.buscarPorId(id)
            );

        }
    }
}
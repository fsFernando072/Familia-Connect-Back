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
import school.sptech.FamiliaConnect.dto.familia.FamiliaRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.model.Endereco;
import school.sptech.FamiliaConnect.model.Familia;
import school.sptech.FamiliaConnect.repository.EnderecoRepository;
import school.sptech.FamiliaConnect.repository.FamiliaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FamiliaServiceTest {

    @Mock
    FamiliaRepository familiaRepository;

    @Mock
    EnderecoRepository enderecoRepository;

    @InjectMocks
    FamiliaService familiaService;

    @Nested
    @DisplayName("Deve salvar a família corretamente")
    class salvar {

        @Test
        @DisplayName("Deve salvar família com sucesso")
        void salvarFamilia() {

            Endereco endereco = new Endereco();
            endereco.setId(1);
            endereco.setCep("08020-000");

            Familia dto = new Familia();
            dto.setEndereco(endereco);
            dto.setFotoFamilia("foto.png");
            dto.setPossuiPrioridade(false);
            dto.setDataCadastro(LocalDate.now());

            Familia familiaEsperada = new Familia();
            familiaEsperada.setId(1);
            familiaEsperada.setEndereco(endereco);
            familiaEsperada.setFotoFamilia("foto.png");

            Mockito.when(enderecoRepository.findById(1))
                    .thenReturn(Optional.of(endereco));

            Mockito.when(familiaRepository.save(Mockito.any(Familia.class)))
                    .thenReturn(familiaEsperada);

            Familia resultado = familiaService.salvar(dto);

            Assertions.assertEquals(1, resultado.getId());
            Assertions.assertNotNull(resultado.getEndereco());
            Assertions.assertEquals("08020-000", resultado.getEndereco().getCep());
        }

        @Test
        @DisplayName("Deve lançar EntidadeNaoEncontradaException quando endereço não for encontrado")
        void salvarFamiliaEnderecoNaoEncontrado() {
            Endereco endereco = new Endereco();
            endereco.setId(99);

            endereco.setCep("08020-000");
            Familia dto = new Familia();
            dto.setEndereco(endereco);

            Mockito.when(enderecoRepository.findById(99))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> familiaService.salvar(dto)
            );
        }
    }

    @Nested
    @DisplayName("Deve retornar listas das famílias corretamente")
    class listar {

        @Test
        @DisplayName("Deve retornar lista com todas as famílias")
        void retornarListaComTodasFamilias() {
            List<Familia> familias = new ArrayList<>();

            Familia familia = new Familia();
            familia.setId(1);
            familia.setFotoFamilia("foto.png");
            familias.add(familia);

            Mockito.when(familiaRepository.findAll())
                    .thenReturn(familias);

            List<Familia> resultado = familiaService.listar();

            Assertions.assertIterableEquals(familias, resultado);
        }

        @Test
        @DisplayName("Deve retornar lista vazia caso não exista nenhuma família")
        void retornarListaVazia() {
            List<Familia> familias = new ArrayList<>();

            Mockito.when(familiaRepository.findAll())
                    .thenReturn(familias);

            List<Familia> resultado = familiaService.listar();

            Assertions.assertTrue(resultado.isEmpty());
        }
    }

    @Nested
    @DisplayName("Deve buscar as famílias corretamente")
    class listarPorId {

        @Test
        @DisplayName("Deve retornar uma família que existe através do seu ID")
        void retornarFamiliaPorId() {
            Familia familia = new Familia();
            familia.setId(1);
            familia.setFotoFamilia("foto.png");

            Mockito.when(familiaRepository.findById(1))
                    .thenReturn(Optional.of(familia));

            Familia resultado = familiaService.listarPorId(1);

            Assertions.assertEquals(1, resultado.getId());
            Assertions.assertEquals("foto.png", resultado.getFotoFamilia());
        }

        @Test
        @DisplayName("Deve lançar EntidadeNaoEncontradaException quando família não for encontrada pelo ID")
        void retornarExceptionFamiliaNaoEncontrada() {
            Integer id = 99;

            Mockito.when(familiaRepository.findById(id))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> familiaService.listarPorId(id)
            );
        }
    }
}


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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import school.sptech.FamiliaConnect.dto.familia.FamiliaListResponseDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.model.Endereco;
import school.sptech.FamiliaConnect.model.Familia;
import school.sptech.FamiliaConnect.repository.EnderecoRepository;
import school.sptech.FamiliaConnect.repository.FamiliaRepository;

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

            Familia familia = new Familia();
            familia.setEndereco(endereco);
            familia.setFotoFamilia("foto.png");
            familia.setPossuiPrioridade(false);

            Familia familiaEsperada = new Familia();
            familiaEsperada.setId(1);
            familiaEsperada.setEndereco(endereco);
            familiaEsperada.setFotoFamilia("foto.png");

            Mockito.when(enderecoRepository.findById(1))
                    .thenReturn(Optional.of(endereco));

            Mockito.when(familiaRepository.save(Mockito.any(Familia.class)))
                    .thenReturn(familiaEsperada);

            Familia resultado = familiaService.salvar(familia);

            Assertions.assertEquals(1, resultado.getId());
            Assertions.assertNotNull(resultado.getEndereco());
            Assertions.assertEquals("08020-000", resultado.getEndereco().getCep());
        }

        @Test
        @DisplayName("Deve lançar EntidadeNaoEncontradaException quando endereço não for encontrado")
        void salvarFamiliaEnderecoNaoEncontrado() {
            Endereco endereco = new Endereco();
            endereco.setId(99);

            Familia familia = new Familia();
            familia.setEndereco(endereco);

            Mockito.when(enderecoRepository.findById(99))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> familiaService.salvar(familia)
            );
        }
    }

    @Nested
    @DisplayName("Deve retornar listas das famílias corretamente")
    class listar {

        @Test
        @DisplayName("Deve retornar página com todas as famílias")
        void retornarPaginaComTodasFamilias() {
            Pageable pageable = PageRequest.of(0, 10);
            FamiliaListResponseDto item = Mockito.mock(FamiliaListResponseDto.class);
            Page<FamiliaListResponseDto> page = new PageImpl<>(List.of(item), pageable, 1);

            Mockito.when(familiaRepository.findAllCustomized(pageable))
                    .thenReturn(page);

            Page<FamiliaListResponseDto> resultado = familiaService.listar(pageable);

            Assertions.assertEquals(1, resultado.getTotalElements());
        }

        @Test
        @DisplayName("Deve retornar página vazia caso não exista nenhuma família")
        void retornarPaginaVazia() {
            Pageable pageable = PageRequest.of(0, 10);
            Page<FamiliaListResponseDto> emptyPage = Page.empty(pageable);

            Mockito.when(familiaRepository.findAllCustomized(pageable))
                    .thenReturn(emptyPage);

            Page<FamiliaListResponseDto> resultado = familiaService.listar(pageable);

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

    @Nested
    @DisplayName("Deve atualizar a família corretamente")
    class atualizar {

        @Test
        @DisplayName("Deve atualizar família com sucesso")
        void atualizarFamilia() {
            Integer id = 1;

            Endereco endereco = new Endereco();
            endereco.setId(1);
            endereco.setCep("08020-000");

            Familia familiaExistente = new Familia();
            familiaExistente.setId(id);
            familiaExistente.setEndereco(endereco);

            Familia familiaAtualizada = new Familia();
            familiaAtualizada.setEndereco(endereco);
            familiaAtualizada.setFotoFamilia("novaFoto.png");
            familiaAtualizada.setPossuiPrioridade(true);

            Mockito.when(familiaRepository.findById(id))
                    .thenReturn(Optional.of(familiaExistente));

            Mockito.when(enderecoRepository.findById(1))
                    .thenReturn(Optional.of(endereco));

            Mockito.when(familiaRepository.save(Mockito.any(Familia.class)))
                    .thenReturn(familiaExistente);

            Familia resultado = familiaService.atualizar(id, familiaAtualizada);

            Assertions.assertNotNull(resultado);
        }

        @Test
        @DisplayName("Deve lançar EntidadeNaoEncontradaException quando família não for encontrada")
        void atualizarFamiliaNaoEncontrada() {
            Integer id = 99;

            Endereco endereco = new Endereco();
            endereco.setId(1);

            Familia familiaAtualizada = new Familia();
            familiaAtualizada.setEndereco(endereco);

            Mockito.when(familiaRepository.findById(id))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> familiaService.atualizar(id, familiaAtualizada)
            );
        }
    }
}

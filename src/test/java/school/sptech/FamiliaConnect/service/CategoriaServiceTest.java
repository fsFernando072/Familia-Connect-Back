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
import school.sptech.FamiliaConnect.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.model.Categoria;
import school.sptech.FamiliaConnect.repository.CategoriaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    CategoriaRepository categoriaRepository;

    @InjectMocks
    CategoriaService categoriaService;

    @Nested
    @DisplayName("Deve retornar as categorias")
    class listar {
        @Test
        @DisplayName("Deve retornar uma lista com todos os cargos")
        void retornarListaComTodosCategorias(){
            List<Categoria> categorias = new ArrayList<>();

            Categoria categoria = new Categoria();

            categoria.setId(1);
            categoria.setNome("categoria1");

            categorias.add(categoria);

            Mockito.when(categoriaRepository.findAll())
                    .thenReturn(categorias);
            List<Categoria> resultado = categoriaService.listar();

            Assertions.assertIterableEquals(categorias, resultado);
        }

    }

    @Nested
    @DisplayName("Deve cadastrar as categorias")
    class salvar {
        @Test
        @DisplayName("Deve criar uma nova categoria")
        void cadastrarCategoria(){
            Categoria categoria = new Categoria();
            categoria.setNome("nomeCategoria");

            Mockito.when(categoriaRepository.save(Mockito.any(Categoria.class)))
                    .thenReturn(categoria);

            Categoria resultado = categoriaService.salvar(categoria);

            Assertions.assertEquals(categoria.getNome(), resultado.getNome());
        }
        @Test
        @DisplayName("Deve retornar EntidadeJaCadastradaException")
        void categoriaDuplicado(){
            String nome = "categoria1";

            Categoria categoria = new Categoria();

            categoria.setNome("categoria1");

            Mockito.when(categoriaRepository.findByNome(nome))
                    .thenReturn(Optional.of(categoria));

            Assertions.assertThrows(
                    EntidadeJaCadastradaException.class,
                    () -> categoriaService.salvar(categoria));
        }
    }
}
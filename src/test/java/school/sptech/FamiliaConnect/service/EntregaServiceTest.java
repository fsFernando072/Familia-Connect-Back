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
import school.sptech.FamiliaConnect.dto.entrega.EntregaRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.EntregaMapper;
import school.sptech.FamiliaConnect.model.Entrega;
import school.sptech.FamiliaConnect.model.Funcionario;
import school.sptech.FamiliaConnect.model.Pessoa;
import school.sptech.FamiliaConnect.model.Produto;
import school.sptech.FamiliaConnect.repository.EntregaRepository;
import school.sptech.FamiliaConnect.repository.FuncionarioRepository;
import school.sptech.FamiliaConnect.repository.PessoaRepository;
import school.sptech.FamiliaConnect.repository.ProdutoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EntregaServiceTest {

    @Mock
    FuncionarioRepository funcionarioRepository;

    @Mock
    PessoaRepository pessoaRepository;

    @Mock
    ProdutoRepository produtoRepository;

    @Mock
    EntregaRepository entregaRepository;

    @InjectMocks
    EntregaService entregaService;

    @Nested
    @DisplayName("Deve listar entregas corretamente")
    class listar {

        @Test
        @DisplayName("Retornar lista de entregas")
        void listarEntregas() {

            List<Entrega> entregas = new ArrayList<>();

            Entrega entrega = new Entrega();
            entrega.setId(1);

            entregas.add(entrega);

            Mockito.when(entregaRepository.findAll())
                    .thenReturn(entregas);

            List<Entrega> resultado = entregaService.listar();

            Assertions.assertIterableEquals(entregas, resultado);
        }

        @Test
        @DisplayName("Retornar lista vazia")
        void listarVazio() {

            List<Entrega> entregas = new ArrayList<>();

            Mockito.when(entregaRepository.findAll())
                    .thenReturn(entregas);

            List<Entrega> resultado = entregaService.listar();

            Assertions.assertTrue(resultado.isEmpty());
        }
    }

    @Nested
    @DisplayName("Deve buscar entrega por id corretamente")
    class listarPorId {

        @Test
        @DisplayName("Buscar entrega existente")
        void buscarEntregaPorId() {

            Entrega entrega = new Entrega();
            entrega.setId(1);

            Mockito.when(entregaRepository.findById(1))
                    .thenReturn(Optional.of(entrega));

            Entrega resultado = entregaService.listarPorId(1);

            Assertions.assertEquals(
                    entrega.getId(),
                    resultado.getId()
            );
        }

        @Test
        @DisplayName("Retornar exception ao buscar id inexistente")
        void buscarIdInexistente() {

            Mockito.when(entregaRepository.findById(1))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> entregaService.listarPorId(1)
            );
        }
    }

    @Nested
    @DisplayName("Deve salvar entregas corretamente")
    class salvar {

        @Test
        @DisplayName("Salvar entrega corretamente")
        void salvarEntrega() {

            Funcionario funcionario = new Funcionario();
            funcionario.setId(1);

            Pessoa pessoa = new Pessoa();
            pessoa.setId(1);

            Produto produto = new Produto();
            produto.setId(1);

            EntregaRequestDto dto = new EntregaRequestDto();

            dto.setDataEntrega(LocalDate.now());
            dto.setIdFuncionario(1);
            dto.setIdPessoa(1);
            dto.setIdProduto(1);

            Entrega entrega = EntregaMapper.toModel(dto);

            entrega.setId(1);
            entrega.setFuncionario(funcionario);
            entrega.setPessoa(pessoa);
            entrega.setProduto(produto);

            Mockito.when(funcionarioRepository.findById(1))
                    .thenReturn(Optional.of(funcionario));

            Mockito.when(pessoaRepository.findById(1))
                    .thenReturn(Optional.of(pessoa));

            Mockito.when(produtoRepository.findById(1))
                    .thenReturn(Optional.of(produto));

            Mockito.when(entregaRepository.save(Mockito.any(Entrega.class)))
                    .thenReturn(entrega);

            Entrega resultado = entregaService.salvar(dto);

            Assertions.assertEquals(entrega.getId(), resultado.getId());
            Assertions.assertEquals(dto.getDataEntrega(), resultado.getDataEntrega());
            Assertions.assertEquals(funcionario.getId(), resultado.getFuncionario().getId());
            Assertions.assertEquals(pessoa.getId(), resultado.getPessoa().getId());
            Assertions.assertEquals(produto.getId(), resultado.getProduto().getId());
        }
    }
}

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
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.exception.EstoqueInsuficienteException;
import school.sptech.FamiliaConnect.model.Entrega;
import school.sptech.FamiliaConnect.model.Familia;
import school.sptech.FamiliaConnect.model.Funcionario;
import school.sptech.FamiliaConnect.model.HistoricoEstoque;
import school.sptech.FamiliaConnect.model.Pessoa;
import school.sptech.FamiliaConnect.model.Produto;
import school.sptech.FamiliaConnect.repository.EntregaRepository;
import school.sptech.FamiliaConnect.repository.FuncionarioRepository;
import school.sptech.FamiliaConnect.repository.HistoricoEstoqueRepository;
import school.sptech.FamiliaConnect.repository.PessoaRepository;
import school.sptech.FamiliaConnect.repository.ProdutoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Mock
    HistoricoEstoqueRepository historicoEstoqueRepository;

    @InjectMocks
    EntregaService entregaService;

    // Helper -------------------------------------------------------------------------------------------------------

    private Pessoa criarPessoaComFamilia(Integer idPessoa, Integer idFamilia) {
        Familia familia = new Familia();
        familia.setId(idFamilia);

        Pessoa pessoa = new Pessoa();
        pessoa.setId(idPessoa);
        pessoa.setFamilia(familia);

        return pessoa;
    }

    private HistoricoEstoque criarHistoricoEstoque(Double quantidade) {
        HistoricoEstoque historicoEstoque = new HistoricoEstoque();
        historicoEstoque.setQuantidade(quantidade);
        return historicoEstoque;
    }

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

            Pessoa pessoa = criarPessoaComFamilia(1, 1);

            Produto produto = new Produto();
            produto.setId(1);

            Entrega dto = new Entrega();

            dto.setDataEntrega(LocalDate.now());
            dto.setFuncionario(funcionario);
            dto.setPessoa(pessoa);
            dto.setProduto(produto);

            Entrega entrega = dto;

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

            Mockito.when(historicoEstoqueRepository.findByProdutoIdAndDataEstoqueBetween(
                            Mockito.eq(1), Mockito.any(), Mockito.any()))
                    .thenReturn(List.of(criarHistoricoEstoque(10.0)));

            Mockito.when(entregaRepository.findByProdutoIdAndDataEntregaBetween(
                            Mockito.eq(1), Mockito.any(), Mockito.any()))
                    .thenReturn(new ArrayList<>());

            Mockito.when(entregaRepository.findByPessoa_FamiliaIdAndDataEntregaBetween(
                            Mockito.eq(1), Mockito.any(), Mockito.any()))
                    .thenReturn(new ArrayList<>());

            Mockito.when(entregaRepository.save(Mockito.any(Entrega.class)))
                    .thenReturn(entrega);

            Entrega resultado = entregaService.salvar(dto);

            Assertions.assertEquals(entrega.getId(), resultado.getId());
            Assertions.assertEquals(dto.getDataEntrega(), resultado.getDataEntrega());
            Assertions.assertEquals(funcionario.getId(), resultado.getFuncionario().getId());
            Assertions.assertEquals(pessoa.getId(), resultado.getPessoa().getId());
            Assertions.assertEquals(produto.getId(), resultado.getProduto().getId());
        }

        @Test
        @DisplayName("Lançar exception quando não há estoque cadastrado no mês para o produto")
        void salvarSemEstoqueDoMes() {

            Funcionario funcionario = new Funcionario();
            funcionario.setId(1);

            Pessoa pessoa = criarPessoaComFamilia(1, 1);

            Produto produto = new Produto();
            produto.setId(1);

            Entrega dto = new Entrega();
            dto.setFuncionario(funcionario);
            dto.setPessoa(pessoa);
            dto.setProduto(produto);

            Mockito.when(funcionarioRepository.findById(1))
                    .thenReturn(Optional.of(funcionario));

            Mockito.when(pessoaRepository.findById(1))
                    .thenReturn(Optional.of(pessoa));

            Mockito.when(produtoRepository.findById(1))
                    .thenReturn(Optional.of(produto));

            Mockito.when(historicoEstoqueRepository.findByProdutoIdAndDataEstoqueBetween(
                            Mockito.eq(1), Mockito.any(), Mockito.any()))
                    .thenReturn(new ArrayList<>());

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> entregaService.salvar(dto)
            );

            Mockito.verify(entregaRepository, Mockito.never()).save(Mockito.any());
        }

        @Test
        @DisplayName("Lançar exception quando a quantidade entregue no mês ultrapassaria o estoque cadastrado")
        void salvarUltrapassandoEstoqueDoMes() {

            Funcionario funcionario = new Funcionario();
            funcionario.setId(1);

            Pessoa pessoa = criarPessoaComFamilia(1, 1);

            Produto produto = new Produto();
            produto.setId(1);

            Entrega dto = new Entrega();
            dto.setFuncionario(funcionario);
            dto.setPessoa(pessoa);
            dto.setProduto(produto);

            Mockito.when(funcionarioRepository.findById(1))
                    .thenReturn(Optional.of(funcionario));

            Mockito.when(pessoaRepository.findById(1))
                    .thenReturn(Optional.of(pessoa));

            Mockito.when(produtoRepository.findById(1))
                    .thenReturn(Optional.of(produto));

            Mockito.when(historicoEstoqueRepository.findByProdutoIdAndDataEstoqueBetween(
                            Mockito.eq(1), Mockito.any(), Mockito.any()))
                    .thenReturn(List.of(criarHistoricoEstoque(1.0)));

            Entrega entregaJaExistente = new Entrega();
            entregaJaExistente.setId(99);

            Mockito.when(entregaRepository.findByProdutoIdAndDataEntregaBetween(
                            Mockito.eq(1), Mockito.any(), Mockito.any()))
                    .thenReturn(List.of(entregaJaExistente));

            Assertions.assertThrows(
                    EstoqueInsuficienteException.class,
                    () -> entregaService.salvar(dto)
            );

            Mockito.verify(entregaRepository, Mockito.never()).save(Mockito.any());
        }

        @Test
        @DisplayName("Lançar exception quando a família já recebeu entrega no mês")
        void salvarFamiliaJaAtendidaNoMes() {

            Funcionario funcionario = new Funcionario();
            funcionario.setId(1);

            Pessoa pessoa = criarPessoaComFamilia(1, 1);

            Produto produto = new Produto();
            produto.setId(1);

            Entrega dto = new Entrega();
            dto.setFuncionario(funcionario);
            dto.setPessoa(pessoa);
            dto.setProduto(produto);

            Mockito.when(funcionarioRepository.findById(1))
                    .thenReturn(Optional.of(funcionario));

            Mockito.when(pessoaRepository.findById(1))
                    .thenReturn(Optional.of(pessoa));

            Mockito.when(produtoRepository.findById(1))
                    .thenReturn(Optional.of(produto));

            Mockito.when(historicoEstoqueRepository.findByProdutoIdAndDataEstoqueBetween(
                            Mockito.eq(1), Mockito.any(), Mockito.any()))
                    .thenReturn(List.of(criarHistoricoEstoque(10.0)));

            Mockito.when(entregaRepository.findByProdutoIdAndDataEntregaBetween(
                            Mockito.eq(1), Mockito.any(), Mockito.any()))
                    .thenReturn(new ArrayList<>());

            Entrega entregaFamiliaJaAtendida = new Entrega();
            entregaFamiliaJaAtendida.setId(50);

            Mockito.when(entregaRepository.findByPessoa_FamiliaIdAndDataEntregaBetween(
                            Mockito.eq(1), Mockito.any(), Mockito.any()))
                    .thenReturn(List.of(entregaFamiliaJaAtendida));

            Assertions.assertThrows(
                    EntidadeJaCadastradaException.class,
                    () -> entregaService.salvar(dto)
            );

            Mockito.verify(entregaRepository, Mockito.never()).save(Mockito.any());
        }
    }

    @Nested
    @DisplayName("Deve atualizar entregas corretamente")
    class atualizar {

        @Test
        @DisplayName("Atualizar entrega existente, ignorando ela mesma nas validações do mês")
        void atualizarEntrega() {

            Funcionario funcionario = new Funcionario();
            funcionario.setId(1);

            Pessoa pessoa = criarPessoaComFamilia(1, 1);

            Produto produto = new Produto();
            produto.setId(1);

            Entrega dto = new Entrega();
            dto.setFuncionario(funcionario);
            dto.setPessoa(pessoa);
            dto.setProduto(produto);

            Entrega entregaAtualizada = new Entrega();
            entregaAtualizada.setId(1);
            entregaAtualizada.setFuncionario(funcionario);
            entregaAtualizada.setPessoa(pessoa);
            entregaAtualizada.setProduto(produto);

            Mockito.when(entregaRepository.existsById(1))
                    .thenReturn(true);

            Mockito.when(funcionarioRepository.findById(1))
                    .thenReturn(Optional.of(funcionario));

            Mockito.when(pessoaRepository.findById(1))
                    .thenReturn(Optional.of(pessoa));

            Mockito.when(produtoRepository.findById(1))
                    .thenReturn(Optional.of(produto));

            Mockito.when(historicoEstoqueRepository.findByProdutoIdAndDataEstoqueBetween(
                            Mockito.eq(1), Mockito.any(), Mockito.any()))
                    .thenReturn(List.of(criarHistoricoEstoque(1.0)));

            Entrega entregaSendoEditada = new Entrega();
            entregaSendoEditada.setId(1);

            Mockito.when(entregaRepository.findByProdutoIdAndDataEntregaBetween(
                            Mockito.eq(1), Mockito.any(), Mockito.any()))
                    .thenReturn(List.of(entregaSendoEditada));

            Mockito.when(entregaRepository.findByPessoa_FamiliaIdAndDataEntregaBetween(
                            Mockito.eq(1), Mockito.any(), Mockito.any()))
                    .thenReturn(List.of(entregaSendoEditada));

            Mockito.when(entregaRepository.save(Mockito.any(Entrega.class)))
                    .thenReturn(entregaAtualizada);

            Entrega resultado = entregaService.atualizar(1, dto);

            Assertions.assertEquals(entregaAtualizada.getId(), resultado.getId());
        }

        @Test
        @DisplayName("Lançar exception ao atualizar entrega inexistente")
        void atualizarEntregaInexistente() {

            Mockito.when(entregaRepository.existsById(1))
                    .thenReturn(false);

            Entrega dto = new Entrega();

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> entregaService.atualizar(1, dto)
            );
        }
    }

    @Nested
    @DisplayName("Deve deletar entregas corretamente")
    class deletar {

        @Test
        @DisplayName("Deletar entrega existente")
        void deletarEntrega() {

            Mockito.when(entregaRepository.existsById(1))
                    .thenReturn(true);

            entregaService.deletar(1);

            Mockito.verify(entregaRepository).deleteById(1);
        }

        @Test
        @DisplayName("Lançar exception ao deletar entrega inexistente")
        void deletarEntregaInexistente() {

            Mockito.when(entregaRepository.existsById(1))
                    .thenReturn(false);

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> entregaService.deletar(1)
            );

            Mockito.verify(entregaRepository, Mockito.never()).deleteById(Mockito.any());
        }
    }
}

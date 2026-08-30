package school.sptech.FamiliaConnect.application.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.application.ports.in.HistoricoEstoqueUseCase;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.domain.entity.HistoricoEstoque;
import school.sptech.FamiliaConnect.domain.entity.Produto;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.HistoricoEstoqueRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoEstoqueService implements HistoricoEstoqueUseCase {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final HistoricoEstoqueRepository historicoEstoqueRepository;
    private final ProdutoRepository produtoRepository;

    // Construtores ----------------------------------------------------------------------------------------------------

    public HistoricoEstoqueService(HistoricoEstoqueRepository historicoEstoqueRepository, ProdutoRepository produtoRepository) {
        this.historicoEstoqueRepository = historicoEstoqueRepository;
        this.produtoRepository = produtoRepository;
    }

    // Funções ---------------------------------------------------------------------------------------------------------

    public List<HistoricoEstoque> listar() {

        return historicoEstoqueRepository.findAll();

    }

    public HistoricoEstoque listarPorId(Integer id) {

        Optional<HistoricoEstoque> historicoEstoque = historicoEstoqueRepository.findById(id);

        if (historicoEstoque.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Histórico de estoque não encontrado pelo id");
        }

        return historicoEstoque.get();

    }

    public HistoricoEstoque salvar(HistoricoEstoque historicoEstoque) {

        Produto produto = produtoRepository.findById(historicoEstoque.getProduto().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado pelo id"));

        historicoEstoque.setProduto(produto);

        return historicoEstoqueRepository.save(historicoEstoque);

    }

    public HistoricoEstoque atualizar(Integer id, HistoricoEstoque historicoEstoque) {

        if (!historicoEstoqueRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Histórico de estoque não encontrado pelo id");
        }

        Produto produto = produtoRepository.findById(historicoEstoque.getProduto().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado pelo id"));

        historicoEstoque.setId(id);
        historicoEstoque.setProduto(produto);

        return historicoEstoqueRepository.save(historicoEstoque);

    }

    public void deletar(Integer id) {

        if (!historicoEstoqueRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Histórico de estoque não encontrado pelo id");
        }

        historicoEstoqueRepository.deleteById(id);

    }

}

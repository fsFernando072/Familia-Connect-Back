package school.sptech.FamiliaConnect.application.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.application.ports.in.ProdutoUseCase;
import school.sptech.FamiliaConnect.domain.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.domain.entity.Categoria;
import school.sptech.FamiliaConnect.domain.entity.Produto;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.CategoriaRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService implements ProdutoUseCase {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;

    // Construtores ----------------------------------------------------------------------------------------------------

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // Funções ---------------------------------------------------------------------------------------------------------

    public List<Produto> listar(){

        return produtoRepository.findAllByAtivoTrue();

    }

    public Produto salvar(Produto produto){

        Categoria categoria = categoriaRepository.findByIdAndAtivoTrue(produto.getCategoria().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria com o id não foi encontrada"));

        if (produtoRepository.existsByNome(produto.getNome())) {
            throw new EntidadeJaCadastradaException("Produto já cadastrado");
        }

        produto.setAtivo(true);
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);

    }

    public Produto listarPorId(Integer id){

        return produtoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado pelo id"));

    }

    public Produto atualizar(Integer id, Produto produto) {

        if (!produtoRepository.existsByIdAndAtivoTrue(id)){
            throw new EntidadeNaoEncontradaException("O produto com o id fornecido não foi encontrado");
        }

        if (!categoriaRepository.existsByIdAndAtivoTrue(produto.getCategoria().getId())){
            throw new EntidadeNaoEncontradaException("A categoria com o id fornecido não foi encontrado");
        }

        if (produtoRepository.existsByNomeAndIdNot(produto.getNome(), id)) {
            throw new EntidadeJaCadastradaException("Produto já cadastrado");
        }

        produto.setId(id);
        produto.setAtivo(true);

        return produtoRepository.save(produto);
    }

    public void deletar(Integer id) {
        Produto produto = listarPorId(id);

        produto.setAtivo(false);
        produtoRepository.save(produto);
    }
}

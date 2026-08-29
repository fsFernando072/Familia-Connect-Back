package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.produto.ProdutoRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.ProdutoMapper;
import school.sptech.FamiliaConnect.model.Categoria;
import school.sptech.FamiliaConnect.model.Endereco;
import school.sptech.FamiliaConnect.model.Produto;
import school.sptech.FamiliaConnect.repository.CategoriaRepository;
import school.sptech.FamiliaConnect.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

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

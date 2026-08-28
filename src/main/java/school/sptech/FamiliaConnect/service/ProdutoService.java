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

        Categoria categoria = categoriaRepository.findById(produto.getCategoria().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria com o id não foi encontrada"));


        produto.setCategoria(categoria);

        return produtoRepository.save(produto);

    }

    public Produto listarPorId(Integer id){

        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()){
            throw new EntidadeNaoEncontradaException("Produto não encontrada pelo id");
        }

        return produto.get();

    }

    public Produto atualizar(Integer id, Produto produto) {

        if(!produtoRepository.existsById(id)){
            throw new EntidadeNaoEncontradaException("O produto com o id fornecido não foi encontrado");
        }

        if(!categoriaRepository.existsById(produto.getCategoria().getId())){
            throw new EntidadeNaoEncontradaException("O produto com o id fornecido não foi encontrado");
        }

        produto.setId(id);

        return produtoRepository.save(produto);
    }

    public void deletar(Integer id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setAtivo(false);
        produtoRepository.save(produto);
    }
}

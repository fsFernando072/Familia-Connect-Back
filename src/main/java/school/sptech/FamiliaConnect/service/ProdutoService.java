package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.produto.ProdutoRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.ProdutoMapper;
import school.sptech.FamiliaConnect.model.Categoria;
import school.sptech.FamiliaConnect.model.Endereco;
import school.sptech.FamiliaConnect.model.Produto;
import school.sptech.FamiliaConnect.repository.CategoriaRepository;
import school.sptech.FamiliaConnect.repository.ProdutoRepository;

import java.util.List;

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

        return produtoRepository.findAll();

    }

    public Produto salvar(ProdutoRequestDto produtoRequestDto){

        Categoria categoria = categoriaRepository.findById(produtoRequestDto.getIdCategoria())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria com o id não foi encontrada"));

        Produto produto = ProdutoMapper.toModel(produtoRequestDto);
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);

    }
}

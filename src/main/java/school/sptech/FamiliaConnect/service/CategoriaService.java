package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.produto.ProdutoRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.ProdutoMapper;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.model.Categoria;
import school.sptech.FamiliaConnect.model.Entrega;
import school.sptech.FamiliaConnect.model.Produto;
import school.sptech.FamiliaConnect.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private CategoriaRepository categoriaRepository;

    // Construtores ----------------------------------------------------------------------------------------------------

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // Funções ---------------------------------------------------------------------------------------------------------

    public List<Categoria> listar(){

        return categoriaRepository.findAll();

    }

    public Categoria salvar(Categoria categoria){
        categoriaRepository.findByNome(categoria.getNome())
                .ifPresent(categorias -> {
                    throw new EntidadeJaCadastradaException("Acesso já cadastrado");
                });
        return categoriaRepository.save(categoria);

    }

    public Categoria listarPorId(Integer id){

        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if(categoria.isEmpty()){
            throw new EntidadeNaoEncontradaException("categoria não encontrada pelo id");
        }

        return categoria.get();

    }

    public Categoria atualizar(Integer id, Categoria categoria) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("A categoria com o id fornecido não foi encontrada");
        }

        categoriaRepository.findByNome(categoria.getNome())
                .ifPresent(categoria1 -> {
                    throw new EntidadeJaCadastradaException("Categoria já cadastrada");
                });

        categoria.setId(id);

        return categoriaRepository.save(categoria);
    }

    public void deletar(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("A categoria com o id fornecido não foi encontrada");
        }

        categoriaRepository.deleteById(id);
    }
}

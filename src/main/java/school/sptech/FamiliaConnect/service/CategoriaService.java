package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.produto.ProdutoRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.ProdutoMapper;
import school.sptech.FamiliaConnect.model.Categoria;
import school.sptech.FamiliaConnect.model.Produto;
import school.sptech.FamiliaConnect.repository.CategoriaRepository;

import java.util.List;

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

        return categoriaRepository.save(categoria);

    }
}

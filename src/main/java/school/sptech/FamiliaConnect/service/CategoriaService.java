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

        return categoriaRepository.findAllByAtivoTrue();

    }

    public Categoria salvar(Categoria categoria){
        if (categoriaRepository.existsByNome(categoria.getNome())) {
            throw new EntidadeJaCadastradaException("Categoria já cadastrada");
        }

        categoria.setAtivo(true);

        return categoriaRepository.save(categoria);

    }

    public Categoria listarPorId(Integer id){
        return categoriaRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria não encontrada pelo id"));

    }

    public Categoria atualizar(Integer id, Categoria categoria) {

        if (!categoriaRepository.existsByIdAndAtivoTrue(id)) {
            throw new EntidadeNaoEncontradaException("A categoria com o id fornecido não foi encontrada");
        }

        if (categoriaRepository.existsByNomeAndIdNot(categoria.getNome(), id)) {
            throw new EntidadeJaCadastradaException("Categoria já cadastrada");
        }

        categoria.setId(id);
        categoria.setAtivo(true);

        return categoriaRepository.save(categoria);
    }

    public void deletar(Integer id) {
        Categoria categoria = listarPorId(id);

        categoria.setAtivo(false);
        categoriaRepository.save(categoria);
    }
}

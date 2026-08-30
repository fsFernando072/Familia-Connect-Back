package school.sptech.FamiliaConnect.application.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.application.ports.in.CategoriaUseCase;
import school.sptech.FamiliaConnect.domain.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.domain.entity.Categoria;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService implements CategoriaUseCase {

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

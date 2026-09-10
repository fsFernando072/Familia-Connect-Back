package school.sptech.FamiliaConnect.application.ports.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import school.sptech.FamiliaConnect.domain.entity.Categoria;

public interface CategoriaUseCase {

    Page<Categoria> listar(String nome, Pageable pageable);
    Categoria salvar(Categoria categoria);
    Categoria listarPorId(Integer id);
    Categoria atualizar(Integer id, Categoria categoria);
    void deletar(Integer id);

}

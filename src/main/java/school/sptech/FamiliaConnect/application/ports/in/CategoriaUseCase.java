package school.sptech.FamiliaConnect.application.ports.in;

import school.sptech.FamiliaConnect.domain.entity.Categoria;

import java.util.List;

public interface CategoriaUseCase {

    List<Categoria> listar();
    Categoria salvar(Categoria categoria);
    Categoria listarPorId(Integer id);
    Categoria atualizar(Integer id, Categoria categoria);
    void deletar(Integer id);

}

package school.sptech.FamiliaConnect.application.ports.in;

import school.sptech.FamiliaConnect.domain.entity.Produto;

import java.util.List;

public interface ProdutoUseCase {

    List<Produto> listar();
    Produto salvar(Produto produto);
    Produto listarPorId(Integer id);
    Produto atualizar(Integer id, Produto produto);
    void deletar(Integer id);


}

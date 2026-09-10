package school.sptech.FamiliaConnect.application.ports.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import school.sptech.FamiliaConnect.domain.entity.Produto;

public interface ProdutoUseCase {

    Page<Produto> listar(String nome, Pageable pageable);
    Produto salvar(Produto produto);
    Produto listarPorId(Integer id);
    Produto atualizar(Integer id, Produto produto);
    void deletar(Integer id);


}

package school.sptech.FamiliaConnect.application.ports.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import school.sptech.FamiliaConnect.domain.entity.HistoricoEstoque;

public interface HistoricoEstoqueUseCase {

    Page<HistoricoEstoque> listar(String nomeProduto, Pageable pageable);
    HistoricoEstoque listarPorId(Integer id);
    HistoricoEstoque salvar(HistoricoEstoque historicoEstoque);
    HistoricoEstoque atualizar(Integer id, HistoricoEstoque historicoEstoque);
    void deletar(Integer id);
}

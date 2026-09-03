package school.sptech.FamiliaConnect.application.ports.in;

import school.sptech.FamiliaConnect.domain.entity.HistoricoEstoque;

import java.util.List;

public interface HistoricoEstoqueUseCase {

    List<HistoricoEstoque> listar();
    HistoricoEstoque listarPorId(Integer id);
    HistoricoEstoque salvar(HistoricoEstoque historicoEstoque);
    HistoricoEstoque atualizar(Integer id, HistoricoEstoque historicoEstoque);
    void deletar(Integer id);
}

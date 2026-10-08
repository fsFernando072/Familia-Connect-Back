package school.sptech.FamiliaConnect.application.ports.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import school.sptech.FamiliaConnect.domain.entity.HistoricoEstoque;

import java.time.YearMonth;

public interface HistoricoEstoqueUseCase {

    HistoricoEstoque listarPorId(Integer id);
    HistoricoEstoque salvar(HistoricoEstoque historicoEstoque);
    HistoricoEstoque atualizar(Integer id, HistoricoEstoque historicoEstoque);
    void deletar(Integer id);
    Page<HistoricoEstoque> listar(String nomeProduto, YearMonth mes, Pageable pageable);
}

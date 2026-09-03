package school.sptech.FamiliaConnect.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.domain.entity.HistoricoEstoque;

import java.time.LocalDate;
import java.util.List;

public interface HistoricoEstoqueRepository extends JpaRepository<HistoricoEstoque, Integer> {

    List<HistoricoEstoque> findByProdutoId(Integer produtoId);

    List<HistoricoEstoque> findByProdutoIdAndDataEstoqueBetween(Integer produtoId, LocalDate dataInicio, LocalDate dataFim);
}

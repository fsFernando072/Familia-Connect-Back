package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.Entrega;
import school.sptech.FamiliaConnect.model.Pessoa;

import java.time.LocalDate;
import java.util.List;

public interface EntregaRepository extends JpaRepository<Entrega, Integer> {

    List<Entrega> findByPessoaIn(List<Pessoa> pessoas);

    List<Entrega> findByProdutoIdAndDataEntregaBetween(Integer produtoId, LocalDate dataInicio, LocalDate dataFim);

    List<Entrega> findByPessoa_FamiliaIdAndDataEntregaBetween(Integer familiaId, LocalDate dataInicio, LocalDate dataFim);
}

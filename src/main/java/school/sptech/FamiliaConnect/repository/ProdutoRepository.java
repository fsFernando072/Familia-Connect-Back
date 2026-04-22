package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}

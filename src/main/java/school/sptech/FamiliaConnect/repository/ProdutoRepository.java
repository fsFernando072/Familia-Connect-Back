package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.Categoria;
import school.sptech.FamiliaConnect.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    Optional<Produto> findByIdAndAtivoTrue(Integer id);
    Boolean existsByNome(String nome);
    Boolean existsByNomeAndIdNot(String nome, Integer id);
    Boolean existsByIdAndAtivoTrue(Integer id);

    List<Produto> findAllByAtivoTrue();
}

package school.sptech.FamiliaConnect.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.domain.entity.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Optional<Categoria> findByIdAndAtivoTrue(Integer id);
    Boolean existsByNome(String nome);
    Boolean existsByNomeAndIdNot(String nome, Integer id);
    Boolean existsByIdAndAtivoTrue(Integer id);

    List<Categoria> findAllByAtivoTrue();
}

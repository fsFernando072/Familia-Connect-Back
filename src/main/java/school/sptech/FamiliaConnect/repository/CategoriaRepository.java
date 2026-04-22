package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}

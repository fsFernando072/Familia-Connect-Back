package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.Categoria;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Optional<Categoria> findByNome(String nome);
}

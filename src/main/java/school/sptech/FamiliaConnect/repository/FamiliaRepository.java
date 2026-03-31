package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.Familia;

public interface FamiliaRepository extends JpaRepository<Familia, Integer> {
}

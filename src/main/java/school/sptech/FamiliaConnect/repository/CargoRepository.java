package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.Cargo;

import java.util.Optional;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
    Optional<Cargo> findByNome(String nome);
}

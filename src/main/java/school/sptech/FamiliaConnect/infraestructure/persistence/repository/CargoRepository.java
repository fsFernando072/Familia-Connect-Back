package school.sptech.FamiliaConnect.infraestructure.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.domain.entity.Cargo;

import java.util.Optional;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
    Optional<Cargo> findByNome(String nome);
    Optional<Cargo> findByNomeAndIdNot(String nome, Integer id);

    Page<Cargo> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}

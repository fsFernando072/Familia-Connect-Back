package school.sptech.FamiliaConnect.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.domain.entity.Auditoria;

public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer> {
}

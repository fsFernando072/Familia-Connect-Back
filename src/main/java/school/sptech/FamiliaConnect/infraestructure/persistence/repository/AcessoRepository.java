package school.sptech.FamiliaConnect.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.domain.entity.Acesso;

import java.util.Optional;

public interface AcessoRepository extends JpaRepository<Acesso, Integer> {

    Optional<Acesso> findByNomeTela(String nomeTela);

}

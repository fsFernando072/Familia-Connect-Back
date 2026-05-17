package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.Acesso;

import java.util.Optional;

public interface AcessoRepository extends JpaRepository<Acesso, Integer> {

    Optional<Acesso> findByNomeTela(String nomeTela);

}

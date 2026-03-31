package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}

package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
}

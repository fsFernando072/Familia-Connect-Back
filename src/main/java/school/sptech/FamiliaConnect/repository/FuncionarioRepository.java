package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.Funcionario;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    Optional<Funcionario> findByCpf(String cpf);

}

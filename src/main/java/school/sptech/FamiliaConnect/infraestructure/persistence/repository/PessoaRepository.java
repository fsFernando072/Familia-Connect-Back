package school.sptech.FamiliaConnect.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.FamiliaConnect.domain.entity.Pessoa;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    public Boolean existsByCpf(String cpf);

    @Query("SELECT p FROM Pessoa p WHERE p.cpf = :cpf AND p.isResponsavel = true")
    Optional<Pessoa> findResponsavelByCpf(@Param("cpf") String cpf);
}

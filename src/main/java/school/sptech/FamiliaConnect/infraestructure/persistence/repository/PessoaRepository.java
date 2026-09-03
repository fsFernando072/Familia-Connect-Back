package school.sptech.FamiliaConnect.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.domain.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    public Boolean existsByCpf(String cpf);
}

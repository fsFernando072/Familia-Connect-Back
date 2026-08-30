package school.sptech.FamiliaConnect.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.domain.entity.Profissao;

public interface ProfissaoRepository extends JpaRepository<Profissao, Integer> {

    public Boolean existsByNome(String nome);

    public Profissao findByNome(String nome);

}

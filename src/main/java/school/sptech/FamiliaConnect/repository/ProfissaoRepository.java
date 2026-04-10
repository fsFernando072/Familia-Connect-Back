package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.Profissao;

public interface ProfissaoRepository extends JpaRepository<Profissao, Integer> {

    public Boolean existsByName(String nome);

}

package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.GrauParentesco;
import school.sptech.FamiliaConnect.model.Profissao;

public interface GrauParentescoRepository extends JpaRepository<GrauParentesco, Integer> {

    public Boolean existsByGrau(String grau);

    public GrauParentesco findByGrau(String grau);
}

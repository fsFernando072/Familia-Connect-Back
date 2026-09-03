package school.sptech.FamiliaConnect.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.domain.entity.GrauParentesco;

public interface GrauParentescoRepository extends JpaRepository<GrauParentesco, Integer> {

    public Boolean existsByGrau(String grau);

    public GrauParentesco findByGrau(String grau);
}

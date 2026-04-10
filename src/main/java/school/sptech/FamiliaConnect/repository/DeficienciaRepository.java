package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.Deficiencia;

public interface DeficienciaRepository extends JpaRepository<Deficiencia, Integer> {

    public Boolean existsByNome(String nome);

}

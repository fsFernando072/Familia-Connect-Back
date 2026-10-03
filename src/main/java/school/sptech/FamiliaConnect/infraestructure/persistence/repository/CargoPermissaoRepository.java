package school.sptech.FamiliaConnect.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.domain.entity.CargoPermissao;

import java.util.List;

public interface CargoPermissaoRepository extends JpaRepository<CargoPermissao, Integer> {
    List<CargoPermissao> findByCargoId(Integer cargoId);
}

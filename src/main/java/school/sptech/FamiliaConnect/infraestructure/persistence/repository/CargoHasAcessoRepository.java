package school.sptech.FamiliaConnect.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.domain.entity.CargoHasAcesso;

import java.util.List;

public interface CargoHasAcessoRepository extends JpaRepository<CargoHasAcesso, Integer> {
    List<CargoHasAcesso> findByCargoId(Integer cargoId);
    Boolean existsByCargoId(Integer cargoId);
    void deleteByCargoId(Integer cargoId);
}

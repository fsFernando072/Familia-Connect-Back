package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.CargoHasAcesso;

import java.util.List;

public interface CargoHasAcessoRepository extends JpaRepository<CargoHasAcesso, Integer> {
    List<CargoHasAcesso> findByCargoId(Integer cargoId);
}

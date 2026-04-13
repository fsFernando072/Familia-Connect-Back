package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    public Estado findByNome(String nome);
}

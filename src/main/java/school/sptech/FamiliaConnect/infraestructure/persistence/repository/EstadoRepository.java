package school.sptech.FamiliaConnect.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.domain.entity.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    public Estado findByNome(String nome);
    public Estado findBySigla(String sigla);
}

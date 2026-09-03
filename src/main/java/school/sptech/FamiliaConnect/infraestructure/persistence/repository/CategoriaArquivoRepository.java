package school.sptech.FamiliaConnect.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.domain.entity.CategoriaArquivo;

import java.util.Optional;

public interface CategoriaArquivoRepository extends JpaRepository<CategoriaArquivo, Integer> {

    Boolean existsByNome(String nome);

    Optional<CategoriaArquivo> findByNome(String nome);

}

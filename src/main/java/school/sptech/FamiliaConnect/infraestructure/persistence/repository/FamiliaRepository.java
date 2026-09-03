package school.sptech.FamiliaConnect.infraestructure.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.FamiliaConnect.infraestructure.web.dto.familia.FamiliaListResponseDto;
import school.sptech.FamiliaConnect.domain.entity.Familia;
import school.sptech.FamiliaConnect.domain.entity.Pessoa;

import java.util.List;
import java.util.Optional;

public interface FamiliaRepository extends JpaRepository<Familia, Integer> {

    @Query("""
            SELECT
                f.id as idFamilia,
                p.nome as nomeResponsavel,
                SUBSTRING_INDEX(p.nome, ' ', -1) as nomeFamilia,
                p.telefone as telefoneResponsavel,
                CASE WHEN f.foto IS NOT NULL THEN CONCAT('/arquivos/', f.foto.id, '/visualizar') ELSE NULL END as fotoFamilia
                FROM Familia as f
                INNER JOIN Pessoa p ON
                p.familia = f
                WHERE p.isResponsavel = true
        """)
    Page<FamiliaListResponseDto> findAllCustomized(Pageable pageable);

    @Query("""
        SELECT
        	p
            FROM Pessoa p
            WHERE p.familia.id = :id
            ORDER BY p.isResponsavel DESC

    """)
    Optional<List<Pessoa>> findByIdFamilia(Integer id);

}

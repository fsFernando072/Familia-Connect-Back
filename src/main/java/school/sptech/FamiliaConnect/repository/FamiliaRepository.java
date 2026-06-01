package school.sptech.FamiliaConnect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.FamiliaConnect.dto.familia.FamiliaListResponseDto;
import school.sptech.FamiliaConnect.model.Familia;
import school.sptech.FamiliaConnect.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface FamiliaRepository extends JpaRepository<Familia, Integer> {

    @Query("""
            SELECT
                f.id as idFamilia,
                p.nome as nomeResponsavel,
                SUBSTRING_INDEX(p.nome, ' ', -1) as nomeFamilia,
                p.telefone as telefoneResponsavel
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

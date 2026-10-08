package school.sptech.FamiliaConnect.infraestructure.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.FamiliaConnect.domain.entity.Entrega;
import school.sptech.FamiliaConnect.domain.entity.Pessoa;
import school.sptech.FamiliaConnect.infraestructure.web.dto.entrega.EntregaHistoricoLinhaProjection;
import school.sptech.FamiliaConnect.infraestructure.web.dto.entrega.FamiliaPendenteEntregaResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface EntregaRepository extends JpaRepository<Entrega, Integer> {

    List<Entrega> findByPessoaIn(List<Pessoa> pessoas);

    List<Entrega> findByProdutoIdAndDataEntregaBetween(Integer produtoId, LocalDate dataInicio, LocalDate dataFim);

    List<Entrega> findByPessoa_FamiliaIdAndDataEntregaBetween(Integer familiaId, LocalDate dataInicio, LocalDate dataFim);

    // Famílias (pelo responsável) que NÃO têm nenhuma entrega entre inicioMes e fimMes.
    // Quando vira o mês, ninguém tem entrega no novo mês e todas as famílias voltam a aparecer.
    @Query(value = """
            SELECT
                f.id as idFamilia,
                p.id as idResponsavel,
                p.nome as nomeResponsavel,
                SUBSTRING_INDEX(p.nome, ' ', -1) as nomeFamilia,
                p.telefone as telefoneResponsavel,
                CASE WHEN f.foto IS NOT NULL THEN CONCAT('/arquivos/', f.foto.id, '/visualizar') ELSE NULL END as fotoFamilia
            FROM Familia as f
            INNER JOIN Pessoa p ON p.familia = f
            WHERE p.isResponsavel = true
            AND LOWER(p.nome) LIKE LOWER(CONCAT('%', :nomeResponsavel, '%'))
            AND NOT EXISTS (
                SELECT e.id FROM Entrega e
                WHERE e.pessoa.familia.id = f.id
                AND e.dataEntrega BETWEEN :inicioMes AND :fimMes
            )
            """,
            countQuery = """
            SELECT COUNT(f.id)
            FROM Familia as f
            INNER JOIN Pessoa p ON p.familia = f
            WHERE p.isResponsavel = true
            AND LOWER(p.nome) LIKE LOWER(CONCAT('%', :nomeResponsavel, '%'))
            AND NOT EXISTS (
                SELECT e.id FROM Entrega e
                WHERE e.pessoa.familia.id = f.id
                AND e.dataEntrega BETWEEN :inicioMes AND :fimMes
            )
            """)
    Page<FamiliaPendenteEntregaResponseDto> findFamiliasPendentesDeEntrega(
            @Param("nomeResponsavel") String nomeResponsavel,
            @Param("inicioMes") LocalDate inicioMes,
            @Param("fimMes") LocalDate fimMes,
            Pageable pageable);

    // Histórico: uma linha por registro de entrega (um produto), dentro do período informado.
    // O agrupamento por família e mês, a ordenação e a paginação são feitos no service.
    @Query("""
            SELECT
                e.id as idEntrega,
                e.dataEntrega as dataDaEntrega,
                pr.nome as nomeProduto,
                f.id as idFamilia,
                SUBSTRING_INDEX(p.nome, ' ', -1) as nomeFamilia,
                p.nome as nomeResponsavel,
                p.telefone as telefoneResponsavel,
                CASE WHEN f.foto IS NOT NULL THEN CONCAT('/arquivos/', f.foto.id, '/visualizar') ELSE NULL END as fotoFamilia
            FROM Entrega as e
            INNER JOIN e.pessoa p
            INNER JOIN p.familia f
            INNER JOIN e.produto pr
            WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nomeResponsavel, '%'))
            AND e.dataEntrega BETWEEN :inicio AND :fim
            ORDER BY e.id
            """)
    List<EntregaHistoricoLinhaProjection> findHistoricoLinhas(
            @Param("nomeResponsavel") String nomeResponsavel,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim);

}

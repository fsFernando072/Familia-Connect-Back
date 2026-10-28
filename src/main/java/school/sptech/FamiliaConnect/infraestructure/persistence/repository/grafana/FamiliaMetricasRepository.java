package school.sptech.FamiliaConnect.infraestructure.persistence.repository.grafana;

import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.FamiliaConnect.domain.entity.Familia;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface FamiliaMetricasRepository extends Repository<Familia, Integer> {

    @Query(value = """
            SELECT COUNT(*) AS valor FROM familia
            """, nativeQuery = true)
    List<Map<String, Object>> painel2();

    @Query(value = """
            SELECT COUNT(*) AS valor FROM pessoa
            """, nativeQuery = true)
    List<Map<String, Object>> painel3();

    @Query(value = """
            SELECT COUNT(*) AS valor FROM familia WHERE possui_prioridade = 1
            """, nativeQuery = true)
    List<Map<String, Object>> painel4();

    @Query(value = """
            SELECT COUNT(*) AS valor
            FROM familia f
            WHERE NOT EXISTS (
                SELECT 1 FROM entrega e
                JOIN pessoa p ON p.id = e.pessoa_id
                WHERE p.familia_id = f.id AND e.data_entrega BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE())
            )
            """, nativeQuery = true)
    List<Map<String, Object>> painel6();

    @Query(value = """
            SELECT CASE t.k WHEN 1 THEN '1 pessoa' WHEN 6 THEN '6 ou mais' ELSE CONCAT(t.k, ' pessoas') END AS 'Tamanho da família', t.total AS 'Famílias'
            FROM (
                SELECT LEAST(n.qtd, 6) AS k, COUNT(*) AS total
                FROM (SELECT familia_id, COUNT(*) AS qtd FROM pessoa GROUP BY familia_id) n
                GROUP BY LEAST(n.qtd, 6)
            ) t
            ORDER BY t.k
            """, nativeQuery = true)
    List<Map<String, Object>> painel27();

    @Query(value = """
            SELECT x.faixa AS 'Faixa etária', COUNT(*) AS 'Pessoas'
            FROM (
                SELECT CASE WHEN i.idade < 12 THEN '0 a 11 anos' WHEN i.idade < 18 THEN '12 a 17 anos' WHEN i.idade < 60 THEN '18 a 59 anos' ELSE '60 anos ou mais' END AS faixa,
                       CASE WHEN i.idade < 12 THEN 1 WHEN i.idade < 18 THEN 2 WHEN i.idade < 60 THEN 3 ELSE 4 END AS ordem
                FROM (SELECT TIMESTAMPDIFF(YEAR, dt_nascimento, CURDATE()) AS idade FROM pessoa WHERE dt_nascimento IS NOT NULL) i
            ) x
            GROUP BY x.ordem, x.faixa
            ORDER BY x.ordem
            """, nativeQuery = true)
    List<Map<String, Object>> painel28();

    @Query(value = """
            SELECT CASE p.sexo WHEN 'MASCULINO' THEN 'Masculino' WHEN 'FEMININO' THEN 'Feminino' ELSE 'Outro' END AS 'Sexo', COUNT(*) AS 'Pessoas'
            FROM pessoa p
            GROUP BY p.sexo
            ORDER BY 2 DESC
            """, nativeQuery = true)
    List<Map<String, Object>> painel29();

    @Query(value = """
            SELECT IF(f.possui_prioridade = 1, 'Com prioridade', 'Sem prioridade') AS 'Prioridade', COUNT(*) AS 'Famílias'
            FROM familia f
            GROUP BY f.possui_prioridade
            ORDER BY 2 DESC
            """, nativeQuery = true)
    List<Map<String, Object>> painel30();

    @Query(value = """
            SELECT COALESCE(NULLIF(TRIM(en.bairro), ''), 'Não informado') AS 'Bairro', COUNT(*) AS 'Famílias'
            FROM familia f
            JOIN endereco en ON en.id = f.endereco_id
            GROUP BY 1
            ORDER BY 2 DESC
            LIMIT 10
            """, nativeQuery = true)
    List<Map<String, Object>> painel31();

    @Query(value = """
            SELECT pf.nome AS 'Profissão', COUNT(*) AS 'Responsáveis'
            FROM pessoa p
            JOIN profissao pf ON pf.id = p.profissao_id
            WHERE p.is_responsavel = 1
            GROUP BY pf.id, pf.nome
            ORDER BY 2 DESC
            LIMIT 10
            """, nativeQuery = true)
    List<Map<String, Object>> painel32();

    @Query(value = """
            SELECT COUNT(*) AS valor FROM familia f WHERE NOT EXISTS (SELECT 1 FROM entrega e JOIN pessoa p ON p.id=e.pessoa_id WHERE p.familia_id=f.id AND (e.data_entrega BETWEEN :inicio AND :fim))
            """, nativeQuery = true)
    List<Map<String, Object>> painel108(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
            SELECT IF(x.atendida=1, 'Atendidas', 'Sem atendimento') AS 'Situação', COUNT(*) AS 'Famílias' FROM (SELECT f.id, EXISTS (SELECT 1 FROM entrega e JOIN pessoa p ON p.id=e.pessoa_id WHERE p.familia_id=f.id AND (e.data_entrega BETWEEN :inicio AND :fim)) AS atendida FROM familia f) x GROUP BY x.atendida ORDER BY x.atendida DESC
            """, nativeQuery = true)
    List<Map<String, Object>> painel109(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
            SELECT COALESCE(NULLIF(TRIM(GROUP_CONCAT( DISTINCT CASE WHEN p.is_responsavel = 1 THEN p.nome END SEPARATOR ', ')), ''), CONCAT('Família #', f.id)) AS 'Família', DATEDIFF(CURDATE(), MAX(e.data_entrega)) AS 'Dias desde a última entrega' FROM familia f LEFT JOIN pessoa p ON p.familia_id=f.id LEFT JOIN entrega e ON e.pessoa_id=p.id GROUP BY f.id HAVING MAX(e.data_entrega) IS NOT NULL ORDER BY 2 DESC LIMIT 10
            """, nativeQuery = true)
    List<Map<String, Object>> painel110();

    @Query(value = """
            SELECT COALESCE(ROUND(AVG(DATEDIFF(CURDATE(), t.ultima)), 0), 0) AS valor FROM (SELECT MAX(e.data_entrega) AS ultima FROM familia f JOIN pessoa p ON p.familia_id=f.id JOIN entrega e ON e.pessoa_id=p.id GROUP BY f.id) t
            """, nativeQuery = true)
    List<Map<String, Object>> painel111();

}

package school.sptech.FamiliaConnect.infraestructure.persistence.repository.grafana;

import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.FamiliaConnect.domain.entity.Entrega;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface EntregaMetricasRepository extends Repository<Entrega, Integer> {

    @Query(value = """
            SELECT COUNT(DISTINCT p.familia_id) AS valor FROM entrega e JOIN pessoa p ON p.id=e.pessoa_id WHERE (e.data_entrega BETWEEN :inicio AND :fim)
            """, nativeQuery = true)
    List<Map<String, Object>> painel5(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
            SELECT COALESCE(ROUND(100 * COUNT(DISTINCT p.familia_id) / NULLIF((SELECT COUNT(*) FROM familia), 0), 1), 0) AS valor FROM entrega e JOIN pessoa p ON p.id=e.pessoa_id WHERE (e.data_entrega BETWEEN :inicio AND :fim)
            """, nativeQuery = true)
    List<Map<String, Object>> painel7(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
            SELECT COUNT(*) AS valor FROM entrega e WHERE (e.data_entrega BETWEEN :inicio AND :fim)
            """, nativeQuery = true)
    List<Map<String, Object>> painel8(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
            SELECT COALESCE(ROUND(COUNT(*) / NULLIF(COUNT(DISTINCT p.familia_id), 0), 1), 0) AS valor
            FROM entrega e
            JOIN pessoa p ON p.id = e.pessoa_id
            WHERE e.data_entrega BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE())
            """, nativeQuery = true)
    List<Map<String, Object>> painel9();

    @Query(value = """
            SELECT COUNT(*) AS valor FROM entrega e WHERE (e.data_entrega BETWEEN :inicio AND :fim)
            """, nativeQuery = true)
    List<Map<String, Object>> painel10(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
            SELECT COALESCE(SUM(GREATEST(b.cadastrado - b.entregue, 0)), 0) AS valor FROM (
            SELECT pr.id, pr.nome, pr.categoria_id,
                   est.qtd AS cadastrado,
                   COALESCE(ent.qtd, 0) AS entregue
            FROM produto pr
            JOIN (SELECT produto_id, SUM(quantidade) AS qtd FROM historico_estoque
                  WHERE data_estoque BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE()) GROUP BY produto_id) est ON est.produto_id = pr.id
            LEFT JOIN (SELECT produto_id, COUNT(*) AS qtd FROM entrega
                       WHERE data_entrega BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE()) GROUP BY produto_id) ent ON ent.produto_id = pr.id
            WHERE pr.ativo = 1
            ) b
            """, nativeQuery = true)
    List<Map<String, Object>> painel11();

    @Query(value = """
            SELECT COUNT(*) AS valor FROM (
            SELECT pr.id, pr.nome, pr.categoria_id,
                   est.qtd AS cadastrado,
                   COALESCE(ent.qtd, 0) AS entregue
            FROM produto pr
            JOIN (SELECT produto_id, SUM(quantidade) AS qtd FROM historico_estoque
                  WHERE data_estoque BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE()) GROUP BY produto_id) est ON est.produto_id = pr.id
            LEFT JOIN (SELECT produto_id, COUNT(*) AS qtd FROM entrega
                       WHERE data_entrega BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE()) GROUP BY produto_id) ent ON ent.produto_id = pr.id
            WHERE pr.ativo = 1
            ) b WHERE b.entregue >= b.cadastrado
            """, nativeQuery = true)
    List<Map<String, Object>> painel12();

    @Query(value = """
            SELECT COUNT(*) AS valor FROM (
            SELECT pr.id, pr.nome, pr.categoria_id,
                   est.qtd AS cadastrado,
                   COALESCE(ent.qtd, 0) AS entregue
            FROM produto pr
            JOIN (SELECT produto_id, SUM(quantidade) AS qtd FROM historico_estoque
                  WHERE data_estoque BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE()) GROUP BY produto_id) est ON est.produto_id = pr.id
            LEFT JOIN (SELECT produto_id, COUNT(*) AS qtd FROM entrega
                       WHERE data_entrega BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE()) GROUP BY produto_id) ent ON ent.produto_id = pr.id
            WHERE pr.ativo = 1
            ) b WHERE b.entregue < b.cadastrado AND b.entregue >= 0.8 * b.cadastrado
            """, nativeQuery = true)
    List<Map<String, Object>> painel13();

    @Query(value = """
            SELECT CAST(DATE_FORMAT(e.data_entrega, '%Y-%m-01') AS DATETIME) AS time,
                   COUNT(*) AS 'Itens entregues',
                   COUNT(DISTINCT p.familia_id) AS 'Famílias atendidas'
            FROM entrega e
            JOIN pessoa p ON p.id = e.pessoa_id
            WHERE e.data_entrega >= DATE_FORMAT(:inicio, '%Y-%m-01') AND e.data_entrega <= :fim
            GROUP BY 1
            ORDER BY 1
            """, nativeQuery = true)
    List<Map<String, Object>> painel15(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
            SELECT c.nome AS 'Categoria', COUNT(*) AS 'Itens'
            FROM entrega e
            JOIN produto pr ON pr.id = e.produto_id
            JOIN categoria c ON c.id = pr.categoria_id
            WHERE (e.data_entrega BETWEEN :inicio AND :fim)
            GROUP BY c.id, c.nome
            ORDER BY 2 DESC
            """, nativeQuery = true)
    List<Map<String, Object>> painel16(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
            SELECT CAST(e.data_entrega AS DATETIME) AS time, COUNT(*) AS 'Itens entregues'
            FROM entrega e
            WHERE (e.data_entrega BETWEEN :inicio AND :fim)
            GROUP BY 1
            ORDER BY 1
            """, nativeQuery = true)
    List<Map<String, Object>> painel17(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
            SELECT ELT(d.dia, 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado') AS 'Dia da semana', d.itens AS 'Itens entregues'
            FROM (
                SELECT DAYOFWEEK(e.data_entrega) AS dia, COUNT(*) AS itens
                FROM entrega e
                WHERE (e.data_entrega BETWEEN :inicio AND :fim)
                GROUP BY DAYOFWEEK(e.data_entrega)
            ) d
            ORDER BY d.dia
            """, nativeQuery = true)
    List<Map<String, Object>> painel18(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
             SELECT
                    pr.nome AS 'Produto',
                    COUNT(*) AS 'Itens entregues'
                FROM entrega e
                JOIN produto pr ON pr.id = e.produto_id
                WHERE e.data_entrega BETWEEN :inicio AND :fim
                GROUP BY pr.id, pr.nome
                ORDER BY COUNT(*) DESC
                LIMIT 10
            """, nativeQuery = true)
    List<Map<String, Object>> painel19(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
            SELECT fu.nome AS 'Funcionário', COUNT(*) AS 'Itens entregues'
            FROM entrega e
            JOIN funcionario fu ON fu.id = e.funcionario_id
            WHERE (e.data_entrega BETWEEN :inicio AND :fim)
            GROUP BY fu.id, fu.nome
            ORDER BY 2 DESC
            LIMIT 10
            """, nativeQuery = true)
    List<Map<String, Object>> painel20(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
            SELECT b.nome AS 'Produto', c.nome AS 'Categoria',
                           b.cadastrado AS 'Cadastrado', b.entregue AS 'Entregue',
                           b.cadastrado - b.entregue AS 'Saldo',
                           ROUND(100 * b.entregue / NULLIF(b.cadastrado, 0), 0) AS '% usado'
                    FROM (
            SELECT pr.id, pr.nome, pr.categoria_id,
                   est.qtd AS cadastrado,
                   COALESCE(ent.qtd, 0) AS entregue
            FROM produto pr
            JOIN (SELECT produto_id, SUM(quantidade) AS qtd FROM historico_estoque
                  WHERE data_estoque BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE()) GROUP BY produto_id) est ON est.produto_id = pr.id
            LEFT JOIN (SELECT produto_id, COUNT(*) AS qtd FROM entrega
                       WHERE data_entrega BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE()) GROUP BY produto_id) ent ON ent.produto_id = pr.id
            WHERE pr.ativo = 1
            ) b
                    JOIN categoria c ON c.id = b.categoria_id
                    ORDER BY 6 DESC, 1
            """, nativeQuery = true)
    List<Map<String, Object>> painel22();

    @Query(value = """
            SELECT c.nome AS 'Categoria', SUM(GREATEST(b.cadastrado - b.entregue, 0)) AS 'Saldo'
                FROM (
            SELECT pr.id, pr.nome, pr.categoria_id,
                   est.qtd AS cadastrado,
                   COALESCE(ent.qtd, 0) AS entregue
            FROM produto pr
            JOIN (SELECT produto_id, SUM(quantidade) AS qtd FROM historico_estoque
                  WHERE data_estoque BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE()) GROUP BY produto_id) est ON est.produto_id = pr.id
            LEFT JOIN (SELECT produto_id, COUNT(*) AS qtd FROM entrega
                       WHERE data_entrega BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE()) GROUP BY produto_id) ent ON ent.produto_id = pr.id
            WHERE pr.ativo = 1
            ) b
                JOIN categoria c ON c.id = b.categoria_id
                GROUP BY c.id, c.nome
                ORDER BY 2 DESC
            """, nativeQuery = true)
    List<Map<String, Object>> painel23();

    @Query(value = """
            SELECT m.mes AS time, SUM(m.entrada) AS 'Entradas de estoque', SUM(m.saida) AS 'Itens entregues'
            FROM (
                SELECT CAST(DATE_FORMAT(h.data_estoque, '%Y-%m-01') AS DATETIME) AS mes, h.quantidade AS entrada, 0 AS saida
                FROM historico_estoque h
                WHERE h.data_estoque >= DATE_FORMAT(:inicio, '%Y-%m-01') AND h.data_estoque <= :fim
                UNION ALL
                SELECT CAST(DATE_FORMAT(e.data_entrega, '%Y-%m-01') AS DATETIME) AS mes, 0 AS entrada, 1 AS saida
                FROM entrega e
                WHERE e.data_entrega >= DATE_FORMAT(:inicio, '%Y-%m-01') AND e.data_entrega <= :fim
            ) m
            GROUP BY m.mes
            ORDER BY m.mes
            """, nativeQuery = true)
    List<Map<String, Object>> painel24(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
            SELECT CAST(DATE_FORMAT(f.data_cadastro, '%Y-%m-01') AS DATETIME) AS time, COUNT(*) AS 'Novas famílias'
            FROM familia f
            WHERE f.data_cadastro >= DATE_FORMAT(:inicio, '%Y-%m-01') AND f.data_cadastro <= :fim
            GROUP BY 1
            ORDER BY 1
            """, nativeQuery = true)
    List<Map<String, Object>> painel26(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
            SELECT COUNT(*) AS valor FROM entrega e JOIN pessoa p ON p.id=e.pessoa_id WHERE p.familia_id=:familiaId AND e.data_entrega BETWEEN :inicio AND :fim
            """, nativeQuery = true)
    List<Map<String, Object>> painel101(@Param("familiaId") Long familiaId, @Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
            SELECT MAX(e.data_entrega) AS valor FROM entrega e JOIN pessoa p ON p.id=e.pessoa_id WHERE p.familia_id=:familiaId
            """, nativeQuery = true)
    List<Map<String, Object>> painel102(@Param("familiaId") Long familiaId);

    @Query(value = """
            SELECT COUNT(*) AS valor FROM pessoa WHERE familia_id=:familiaId
            """, nativeQuery = true)
    List<Map<String, Object>> painel103(@Param("familiaId") Long familiaId);

    @Query(value = """
            SELECT DATEDIFF(CURDATE(), MAX(e.data_entrega)) AS valor FROM entrega e JOIN pessoa p ON p.id=e.pessoa_id WHERE p.familia_id=:familiaId
            """, nativeQuery = true)
    List<Map<String, Object>> painel104(@Param("familiaId") Long familiaId);

    @Query(value = """
            SELECT CAST(DATE_FORMAT(e.data_entrega, '%Y-%m-01') AS DATETIME) AS time, COUNT(*) AS 'Itens recebidos' FROM entrega e JOIN pessoa p ON p.id=e.pessoa_id WHERE p.familia_id=:familiaId AND e.data_entrega BETWEEN :inicio AND :fim GROUP BY 1 ORDER BY 1
            """, nativeQuery = true)
    List<Map<String, Object>> painel105(@Param("familiaId") Long familiaId, @Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
            SELECT pr.nome AS 'Produto', COUNT(*) AS 'Quantidade recebida' FROM entrega e JOIN pessoa p ON p.id=e.pessoa_id JOIN produto pr ON pr.id=e.produto_id WHERE p.familia_id=:familiaId AND e.data_entrega BETWEEN :inicio AND :fim GROUP BY pr.id, pr.nome ORDER BY 2 DESC LIMIT 10
            """, nativeQuery = true)
    List<Map<String, Object>> painel106(@Param("familiaId") Long familiaId, @Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

    @Query(value = """
            SELECT e.data_entrega AS 'Data', pr.nome AS 'Produto', fu.nome AS 'Funcionário' FROM entrega e JOIN pessoa p ON p.id=e.pessoa_id JOIN produto pr ON pr.id=e.produto_id LEFT JOIN funcionario fu ON fu.id=e.funcionario_id WHERE p.familia_id=:familiaId AND e.data_entrega BETWEEN :inicio AND :fim ORDER BY e.data_entrega DESC LIMIT 30
            """, nativeQuery = true)
    List<Map<String, Object>> painel107(@Param("familiaId") Long familiaId, @Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

}

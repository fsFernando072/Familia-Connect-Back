package school.sptech.FamiliaConnect.infraestructure.web.dto.entrega;

import java.time.LocalDate;

// Uma linha por registro de entrega (um produto). O service agrupa essas linhas por família e mês.
// Os nomes dos getters precisam bater com os aliases do SELECT (camelCase).
public interface EntregaHistoricoLinhaProjection {

    Integer getIdEntrega();

    LocalDate getDataDaEntrega();

    String getNomeProduto();

    Integer getIdFamilia();

    String getNomeFamilia();

    String getNomeResponsavel();

    String getTelefoneResponsavel();

    String getFotoFamilia();
}

package school.sptech.FamiliaConnect.infraestructure.web.dto.entrega;

// Projeção da query de famílias que ainda não receberam entrega no mês.
// Os nomes dos getters precisam bater com os aliases do SELECT (camelCase).
public interface FamiliaPendenteEntregaResponseDto {

    Integer getIdFamilia();

    Integer getIdResponsavel();

    String getNomeResponsavel();

    String getNomeFamilia();

    String getTelefoneResponsavel();

    String getFotoFamilia();
}

package school.sptech.FamiliaConnect.domain.enums;

import java.util.List;

/**
 * Nível de permissionamento de um cargo sobre uma página.
 * Os níveis são cumulativos: cada um inclui as ações do anterior.
 */
public enum NivelAcessoEnum {

    VISUALIZACAO("listar"),
    LISTAS_CADASTROS("listar", "cadastrar", "editar"),
    ADMINISTRADOR("listar", "cadastrar", "editar", "excluir");

    private final List<String> acoes;

    NivelAcessoEnum(String... acoes) {
        this.acoes = List.of(acoes);
    }

    public List<String> getAcoes() {
        return acoes;
    }
}

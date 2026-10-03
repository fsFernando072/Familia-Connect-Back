package school.sptech.FamiliaConnect.domain.enums;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Páginas (módulos) do sistema que um cargo pode acessar.
 * Cada página aponta para os "recursos" do back-end que ela controla. As authorities continuam no formato
 * <acao>_<recurso> (ex.: listar_produtos), então os @PreAuthorize dos controllers não precisam mudar.
 */
public enum PaginaEnum {

    FAMILIAS("familias", "profissoes"),
    FUNCIONARIOS("funcionarios", "auditorias"),
    PRODUTOS("produtos"),
    CARGOS("cargos"),
    CATEGORIAS("categorias"),
    HISTORICO_ENTREGAS("entregas"),
    HISTORICO_ESTOQUE("estoques"),
    DASHBOARD(); // só controla a tela no front-end, não há endpoint próprio

    // Qualquer cargo que tenha ao menos uma página pode ver as fotos (fotos de funcionários, produtos etc.).
    public static final List<String> AUTORIDADES_BASE = List.of("visualizar_arquivos");

    private final List<String> recursos;

    PaginaEnum(String... recursos) {
        this.recursos = List.of(recursos);
    }

    public Set<String> autoridades(NivelAcessoEnum nivel) {
        Set<String> autoridades = new LinkedHashSet<>();

        for (String recurso : recursos) {
            for (String acao : nivel.getAcoes()) {
                autoridades.add(acao + "_" + recurso);
            }
        }

        return autoridades;
    }
}

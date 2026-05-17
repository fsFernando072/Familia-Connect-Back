package school.sptech.FamiliaConnect.enums;

import java.util.Arrays;

public enum AcessoEnum {
    LISTAR_FUNCIONARIOS(Constants.LISTAR_FUNCIONARIOS),
    LISTAR_PRODUTOS(Constants.LISTAR_PRODUTOS),
    LISTAR_FAMILIAS(Constants.LISTAR_FAMILIAS),
    LISTAR_ENTREGAS(Constants.LISTAR_ENTREGAS),

    CADASTRAR_FUNCIONARIOS(Constants.CADASTRAR_FUNCIONARIOS),
    CADASTRAR_PRODUTOS(Constants.CADASTRAR_PRODUTOS),
    CADASTRAR_FAMILIAS(Constants.CADASTRAR_FAMILIAS),
    CADASTRAR_ENTREGAS(Constants.CADASTRAR_ENTREGAS),

    EDITAR_FUNCIONARIOS(Constants.EDITAR_FUNCIONARIOS),
    EDITAR_PRODUTOS(Constants.EDITAR_PRODUTOS),
    EDITAR_FAMILIAS(Constants.EDITAR_FAMILIAS),
    EDITAR_ENTREGAS(Constants.EDITAR_ENTREGAS),

    EXCLUIR_FUNCIONARIOS(Constants.EXCLUIR_FUNCIONARIOS),
    EXCLUIR_PRODUTOS(Constants.EXCLUIR_PRODUTOS),
    EXCLUIR_FAMILIAS(Constants.EXCLUIR_FAMILIAS),
    EXCLUIR_ENTREGAS(Constants.EXCLUIR_ENTREGAS);

    private String valor;

    AcessoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static Boolean validateEnum(String value) {
        return Arrays.stream(AcessoEnum.values())
                .anyMatch(acesso -> acesso.getValor().equals(value));
    }

    public static class Constants {
        public static final String LISTAR_FUNCIONARIOS    = "listar_funcionarios";
        public static final String LISTAR_PRODUTOS        = "listar_produtos";
        public static final String LISTAR_FAMILIAS        = "listar_familias";
        public static final String LISTAR_ENTREGAS        = "listar_entregas";

        public static final String CADASTRAR_FUNCIONARIOS = "cadastrar_funcionarios";
        public static final String CADASTRAR_PRODUTOS     = "cadastrar_produtos";
        public static final String CADASTRAR_FAMILIAS     = "cadastrar_familias";
        public static final String CADASTRAR_ENTREGAS     = "cadastrar_entregas";

        public static final String EDITAR_FUNCIONARIOS    = "editar_funcionarios";
        public static final String EDITAR_PRODUTOS        = "editar_produtos";
        public static final String EDITAR_FAMILIAS        = "editar_familias";
        public static final String EDITAR_ENTREGAS        = "editar_entregas";

        public static final String EXCLUIR_FUNCIONARIOS   = "excluir_funcionarios";
        public static final String EXCLUIR_PRODUTOS       = "excluir_produtos";
        public static final String EXCLUIR_FAMILIAS       = "excluir_familias";
        public static final String EXCLUIR_ENTREGAS       = "excluir_entregas";
    }
}

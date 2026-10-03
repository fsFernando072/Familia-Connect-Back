package school.sptech.FamiliaConnect.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import school.sptech.FamiliaConnect.domain.entity.Cargo;
import school.sptech.FamiliaConnect.domain.entity.CargoPermissao;
import school.sptech.FamiliaConnect.domain.entity.Funcionario;
import school.sptech.FamiliaConnect.domain.enums.NivelAcessoEnum;
import school.sptech.FamiliaConnect.domain.enums.PaginaEnum;
import school.sptech.FamiliaConnect.infraestructure.web.dto.funcionario.FuncionarioDetalhesDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class PermissoesDoCargoTest {

    private Set<String> autoridades(List<CargoPermissao> permissoes) {
        FuncionarioDetalhesDto detalhes = new FuncionarioDetalhesDto(new Funcionario("11111111111", "senha"), permissoes);

        return detalhes.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }

    private CargoPermissao permissao(PaginaEnum pagina, NivelAcessoEnum nivel) {
        return new CargoPermissao(new Cargo(), pagina, nivel);
    }

    @Test
    @DisplayName("Administrador deve listar, cadastrar, editar e excluir na página")
    void administrador() {
        Set<String> resultado = autoridades(List.of(permissao(PaginaEnum.PRODUTOS, NivelAcessoEnum.ADMINISTRADOR)));

        Assertions.assertTrue(resultado.containsAll(Set.of(
                "listar_produtos", "cadastrar_produtos", "editar_produtos", "excluir_produtos")));
    }

    @Test
    @DisplayName("Listas e cadastros não deve poder excluir")
    void listasECadastros() {
        Set<String> resultado = autoridades(List.of(permissao(PaginaEnum.PRODUTOS, NivelAcessoEnum.LISTAS_CADASTROS)));

        Assertions.assertTrue(resultado.containsAll(Set.of("listar_produtos", "cadastrar_produtos", "editar_produtos")));
        Assertions.assertFalse(resultado.contains("excluir_produtos"));
    }

    @Test
    @DisplayName("Visualização deve apenas listar")
    void visualizacao() {
        Set<String> resultado = autoridades(List.of(permissao(PaginaEnum.CATEGORIAS, NivelAcessoEnum.VISUALIZACAO)));

        Assertions.assertTrue(resultado.contains("listar_categorias"));
        Assertions.assertFalse(resultado.contains("cadastrar_categorias"));
        Assertions.assertFalse(resultado.contains("editar_categorias"));
        Assertions.assertFalse(resultado.contains("excluir_categorias"));
    }

    @Test
    @DisplayName("Página sem permissão não concede acesso a ela")
    void paginaNaoMarcada() {
        Set<String> resultado = autoridades(List.of(permissao(PaginaEnum.PRODUTOS, NivelAcessoEnum.ADMINISTRADOR)));

        Assertions.assertFalse(resultado.contains("listar_familias"));
    }

    @Test
    @DisplayName("Cargo sem nenhuma página não tem nenhuma authority")
    void semPermissoes() {
        Assertions.assertTrue(autoridades(List.of()).isEmpty());
    }
}

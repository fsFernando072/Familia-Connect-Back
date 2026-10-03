package school.sptech.FamiliaConnect.infraestructure.web.dto.funcionario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.FamiliaConnect.domain.entity.CargoPermissao;
import school.sptech.FamiliaConnect.domain.entity.Funcionario;
import school.sptech.FamiliaConnect.domain.enums.PaginaEnum;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FuncionarioDetalhesDto implements UserDetails {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final String nome;
    private final String cpf;
    private final String senha;
    private final List<CargoPermissao> permissoes;

    // Construtores ----------------------------------------------------------------------------------------------------
    public FuncionarioDetalhesDto(Funcionario funcionario, List<CargoPermissao> permissoes) {
        this.nome = funcionario.getNome();
        this.senha = funcionario.getSenha();
        this.cpf = funcionario.getCpf();
        this.permissoes = permissoes;
    }

    public String getNome() {
        return nome;
    }

    public List<CargoPermissao> getPermissoes() {
        return permissoes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Converte página + nível do cargo nas authorities <acao>_<recurso> que os @PreAuthorize já usam
        Set<String> nomes = new LinkedHashSet<>();

        for (CargoPermissao permissao : this.permissoes) {
            nomes.addAll(permissao.getPagina().autoridades(permissao.getNivel()));
        }

        if (!nomes.isEmpty()) {
            nomes.addAll(PaginaEnum.AUTORIDADES_BASE);
        }

        return nomes.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

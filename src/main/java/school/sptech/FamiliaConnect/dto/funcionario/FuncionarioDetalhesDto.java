package school.sptech.FamiliaConnect.dto.funcionario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.FamiliaConnect.model.Funcionario;

import java.util.Collection;

public class FuncionarioDetalhesDto implements UserDetails {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final String nome;
    private final String cpf;
    private final String senha;

    // Construtores ----------------------------------------------------------------------------------------------------
    public FuncionarioDetalhesDto(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.senha = funcionario.getSenha();
        this.cpf = funcionario.getCpf();
    }

    public String getNome() {
        return nome;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

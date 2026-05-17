package school.sptech.FamiliaConnect.dto.funcionario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.FamiliaConnect.model.Acesso;
import school.sptech.FamiliaConnect.model.CargoHasAcesso;
import school.sptech.FamiliaConnect.model.Funcionario;

import java.util.Collection;
import java.util.List;

public class FuncionarioDetalhesDto implements UserDetails {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final String nome;
    private final String cpf;
    private final String senha;
    private final List<CargoHasAcesso> acessos;

    // Construtores ----------------------------------------------------------------------------------------------------
    public FuncionarioDetalhesDto(Funcionario funcionario, List<CargoHasAcesso> acessos) {
        this.nome = funcionario.getNome();
        this.senha = funcionario.getSenha();
        this.cpf = funcionario.getCpf();
        this.acessos = acessos;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //mapeando os acessos para o tipo que Spring security entende para colocar no payload do token
        return this.acessos.stream()
                .map(acesso -> new SimpleGrantedAuthority(acesso.getAcesso().getNomeTela()))
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

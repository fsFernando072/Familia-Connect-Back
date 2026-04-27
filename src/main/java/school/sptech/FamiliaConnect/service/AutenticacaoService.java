package school.sptech.FamiliaConnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.funcionario.FuncionarioDetalhesDto;
import school.sptech.FamiliaConnect.model.Funcionario;
import school.sptech.FamiliaConnect.repository.FuncionarioRepository;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Métodos ---------------------------------------------------------------------------------------------------------
    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {

        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findByCpf(cpf);

        if (funcionarioOpt.isEmpty()) {

            throw new UsernameNotFoundException(String.format("usuário: %s não encontrado", cpf));
        }

        return new FuncionarioDetalhesDto(funcionarioOpt.get());
    }
}

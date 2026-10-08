package school.sptech.FamiliaConnect.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.infraestructure.web.dto.funcionario.FuncionarioDetalhesDto;
import school.sptech.FamiliaConnect.domain.entity.CargoPermissao;
import school.sptech.FamiliaConnect.domain.entity.Funcionario;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.CargoPermissaoRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.FuncionarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoPermissaoRepository cargoPermissaoRepository;

    // Métodos ---------------------------------------------------------------------------------------------------------
    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {

        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findByCpf(cpf);

        if (funcionarioOpt.isEmpty()) {

            throw new UsernameNotFoundException(String.format("usuário: %s não encontrado", cpf));
        }

        List<CargoPermissao> permissoes = cargoPermissaoRepository.findByCargoId(funcionarioOpt.get().getCargo().getId());

        return new FuncionarioDetalhesDto(funcionarioOpt.get(), permissoes);
    }
}

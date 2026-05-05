package school.sptech.FamiliaConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.model.Funcionario;
import school.sptech.FamiliaConnect.repository.CargoRepository;
import school.sptech.FamiliaConnect.repository.FuncionarioRepository;

import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (repository.findByCpf("52437201866").isEmpty()) {

            Optional<Cargo> cargo = cargoRepository.findById(1);

            Funcionario f = new Funcionario();
            f.setNome("João Silva");
            f.setCpf("52437201866");
            f.setSenha(passwordEncoder.encode("senha123"));
            f.setCargo(cargo.get());

            repository.save(f);
            System.out.println("Usuário inicial criado com sucesso!");
        }
    }
}

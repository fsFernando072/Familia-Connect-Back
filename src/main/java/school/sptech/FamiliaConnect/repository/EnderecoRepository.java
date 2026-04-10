package school.sptech.FamiliaConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    public Boolean existsByLogradouroAndNumero(String logradouro, Integer numero);
}

package school.sptech.FamiliaConnect.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.domain.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    public Boolean existsByLogradouroAndNumero(String logradouro, String numero);
}

package school.sptech.FamiliaConnect.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.FamiliaConnect.model.Arquivo;
import school.sptech.FamiliaConnect.model.CategoriaArquivo;


// Etapa 2: camada de acesso a dados da entidade Arquivo.
public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {

    List<Arquivo> findByCategoriaArquivo(CategoriaArquivo categoria);
}

package school.sptech.FamiliaConnect.application.ports.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import school.sptech.FamiliaConnect.domain.entity.Cargo;

public interface CargoUseCase {

    Cargo cadastrar(Cargo cargo);
    Page<Cargo> listar(String nome, Pageable pageable);
    Cargo buscarPorId(Integer id);
    Cargo atualizar(Integer id, Cargo cargo);
    void deletar(Integer id);

}

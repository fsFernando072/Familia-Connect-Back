package school.sptech.FamiliaConnect.application.ports.in;

import school.sptech.FamiliaConnect.domain.entity.Cargo;

import java.util.List;

public interface CargoUseCase {

    Cargo cadastrar(Cargo cargo);
    List<Cargo> listar();
    Cargo buscarPorId(Integer id);
    Cargo atualizar(Integer id, Cargo cargo);
    void deletar(Integer id);

}

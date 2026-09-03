package school.sptech.FamiliaConnect.application.ports.in;

import school.sptech.FamiliaConnect.domain.entity.CargoHasAcesso;

import java.util.List;

public interface CargoHasAcessoUseCase {

    CargoHasAcesso cadastrar(CargoHasAcesso cargoHasAcesso);
    List<CargoHasAcesso> listar();
    CargoHasAcesso buscarPorId(Integer id);
    CargoHasAcesso atualizar(Integer id, CargoHasAcesso cargoHasAcesso);
    void deletar(Integer id);

}

package school.sptech.FamiliaConnect.application.ports.in;

import school.sptech.FamiliaConnect.domain.entity.Entrega;

import java.util.List;

public interface EntregaUseCase {

    List<Entrega> listar();
    Entrega listarPorId(Integer id);
    Entrega salvar(Entrega entrega);
    Entrega atualizar(Integer id, Entrega entrega);
    void deletar(Integer id);

}

package school.sptech.FamiliaConnect.application.ports.in;

import school.sptech.FamiliaConnect.domain.entity.Estado;

import java.util.List;

public interface EstadoUseCase {

    List<Estado> listar();
    Estado buscarPorId(Integer id);

}

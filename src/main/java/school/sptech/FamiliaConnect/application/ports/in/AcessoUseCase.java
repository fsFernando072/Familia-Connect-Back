package school.sptech.FamiliaConnect.application.ports.in;

import school.sptech.FamiliaConnect.domain.entity.Acesso;

import java.util.List;

public interface AcessoUseCase {

    Acesso cadastrar(Acesso acesso);
    List<Acesso> listar();
    Acesso buscarPorId(Integer id);
    Acesso atualizar(Integer id, Acesso acesso);
    void deletar(Integer id);
}

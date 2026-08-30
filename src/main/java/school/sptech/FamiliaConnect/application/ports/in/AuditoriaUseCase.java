package school.sptech.FamiliaConnect.application.ports.in;

import school.sptech.FamiliaConnect.domain.entity.Auditoria;

import java.util.List;

public interface AuditoriaUseCase {

    Auditoria cadastrar(Auditoria auditoria);
    List<Auditoria> listar();
    Auditoria atualizar(Integer id, Auditoria auditoria);
    Auditoria buscarPorId(Integer id);
    void deletar(Integer id);
}

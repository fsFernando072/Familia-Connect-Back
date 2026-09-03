package school.sptech.FamiliaConnect.application.ports.in;

import school.sptech.FamiliaConnect.domain.entity.Arquivo;

import java.util.List;

public interface ArquivoUseCase {

    Arquivo salvar(Arquivo arquivo);

    List<Arquivo> listar();

    List<Arquivo> listarPorCategoriaArquivo(String categoriaNome);

    Arquivo buscarPorId(Integer id);

    void deletarPorId(Integer id);
}

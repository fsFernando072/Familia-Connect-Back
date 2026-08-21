package school.sptech.FamiliaConnect.service;

import school.sptech.FamiliaConnect.model.Arquivo;

import java.util.List;

public interface ArquivoService {

    Arquivo salvar(Arquivo arquivo);

    List<Arquivo> listar();

    List<Arquivo> listarPorCategoriaArquivo(String categoriaNome);

    Arquivo buscarPorId(Integer id);

    void deletarPorId(Integer id);
}

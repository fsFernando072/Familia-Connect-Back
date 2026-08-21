package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.model.CategoriaArquivo;
import school.sptech.FamiliaConnect.repository.CategoriaArquivoRepository;

import java.util.List;

@Service
public class CategoriaArquivoService {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final CategoriaArquivoRepository categoriaArquivoRepository;

    // Construtores ----------------------------------------------------------------------------------------------------

    public CategoriaArquivoService(CategoriaArquivoRepository categoriaArquivoRepository) {
        this.categoriaArquivoRepository = categoriaArquivoRepository;
    }

    // Funções ---------------------------------------------------------------------------------------------------------

    public CategoriaArquivo cadastrarCategoria(CategoriaArquivo categoriaArquivo){

        if (categoriaArquivoRepository.existsByNome(categoriaArquivo.getNome())){
            throw new EntidadeJaCadastradaException("Categoria de arquivo já cadastrada");
        }

        return categoriaArquivoRepository.save(categoriaArquivo);
    }


    public CategoriaArquivo buscarPorNome(String nome){

        return categoriaArquivoRepository.findByNome(nome)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria de arquivo \"" + nome + "\" não encontrada"));
    }

    public List<CategoriaArquivo> listarCategorias(){

        return categoriaArquivoRepository.findAll();
    }
}

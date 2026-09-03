package school.sptech.FamiliaConnect.infraestructure.persistence.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.FamiliaConnect.application.ports.in.ArquivoUseCase;
import school.sptech.FamiliaConnect.domain.entity.Arquivo;
import school.sptech.FamiliaConnect.domain.entity.CategoriaArquivo;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.ArquivoRepository;
import school.sptech.FamiliaConnect.application.service.CategoriaArquivoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;

@Service
@ConditionalOnProperty(name = "app.storage.type", havingValue = "local")
public class ArquivoLocalServiceImpl implements ArquivoUseCase {

    private final Path pastaBase;
    private final ArquivoRepository arquivoRepository;
    private final CategoriaArquivoService categoriaArquivoService;

    public ArquivoLocalServiceImpl(@Value("${app.storage.local.path:uploads}") String pastaBase,
                                   ArquivoRepository arquivoRepository,
                                   CategoriaArquivoService categoriaArquivoService) {
        this.pastaBase = Path.of(pastaBase);
        this.arquivoRepository = arquivoRepository;
        this.categoriaArquivoService = categoriaArquivoService;

        try {
            Files.createDirectories(this.pastaBase);
        } catch (IOException ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Falha ao criar diretório para armazenamento local de arquivos.",
                    ex
            );
        }
    }

    @Override
    public Arquivo salvar(Arquivo arquivo) {

        CategoriaArquivo categoria = arquivo.getCategoriaArquivo();

        Path pastaCategoria = pastaBase.resolve(categoria.getNome());
        try {
            Files.createDirectories(pastaCategoria);
        } catch (IOException ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Falha ao criar diretório para armazenamento local de arquivos.",
                    ex
            );
        }

        String nomeFisico = categoria.getNome() + "/" + UUID.randomUUID() + "-" + arquivo.getNomeOriginal();
        Path caminhoDestino = pastaBase.resolve(nomeFisico);
        try {
            Files.write(caminhoDestino, arquivo.getDados(), StandardOpenOption.CREATE_NEW,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Falha ao salvar arquivo no armazenamento local.",
                    e
            );
        }
        arquivo.setDados(null);
        arquivo.setNomeGerado(nomeFisico);
        return arquivoRepository.save(arquivo);
    }

    @Override
    public List<Arquivo> listar() {
        return arquivoRepository.findAll();
    }

    @Override
    public List<Arquivo> listarPorCategoriaArquivo(String categoriaNome) {
        CategoriaArquivo categoria = categoriaArquivoService.buscarPorNome(categoriaNome);
        return arquivoRepository.findByCategoriaArquivo(categoria);
    }

    @Override
    public Arquivo buscarPorId(Integer id) {
        Arquivo arquivo = arquivoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Arquivo não encontrado."));

        Path caminhoFisico = pastaBase.resolve(arquivo.getNomeGerado());
        if (!Files.exists(caminhoFisico)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Arquivo físico não encontrado no armazenamento local.");
        }
        try {
            byte[] dados = Files.readAllBytes(caminhoFisico);
            arquivo.setDados(dados);
            return arquivo;
        } catch (IOException ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Falha ao ler arquivo do armazenamento local.",
                    ex
            );
        }
    }

    @Override
    public void deletarPorId(Integer id) {
        Arquivo arquivo = buscarPorId(id);
        Path caminhoFisico = pastaBase.resolve(arquivo.getNomeGerado());
        try {
            Files.deleteIfExists(caminhoFisico);
        } catch (IOException ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Falha ao deletar arquivo do armazenamento local.",
                    ex
            );
        }
        arquivoRepository.delete(arquivo);
    }
}

package school.sptech.FamiliaConnect.service.implementations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.FamiliaConnect.model.Arquivo;
import school.sptech.FamiliaConnect.model.CategoriaArquivo;
import school.sptech.FamiliaConnect.repository.ArquivoRepository;
import school.sptech.FamiliaConnect.service.ArquivoService;
import school.sptech.FamiliaConnect.service.CategoriaArquivoService;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@ConditionalOnProperty(name = "app.storage.type", havingValue = "s3")
public class ArquivoS3ServiceImpl implements ArquivoService {

    private final S3Client s3Client;
    private final String bucket;
    private final ArquivoRepository arquivoRepository;
    private final CategoriaArquivoService categoriaArquivoService;

    public ArquivoS3ServiceImpl(S3Client s3Client, @Value("${app.storage.s3.bucket}") String bucket,
                                ArquivoRepository arquivoRepository,
                                CategoriaArquivoService categoriaArquivoService) {
        this.s3Client = s3Client;
        this.bucket = bucket;
        this.arquivoRepository = arquivoRepository;
        this.categoriaArquivoService = categoriaArquivoService;
    }

    @Override
    public Arquivo salvar(Arquivo arquivo) {

        CategoriaArquivo categoria = arquivo.getCategoriaArquivo();

        String chave = categoria.getNome() + "/" + UUID.randomUUID() + "-" + arquivo.getNomeOriginal();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(chave)
                .contentType(arquivo.getMimeType())
                .contentLength(arquivo.getTamanho())
                .build();

        try {
            s3Client.putObject(request, RequestBody.fromBytes(arquivo.getDados()));
        } catch (S3Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Falha ao salvar arquivo no S3.",
                    ex
            );
        }
        arquivo.setDados(null);
        arquivo.setNomeGerado(chave);
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

        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucket)
                .key(arquivo.getNomeGerado())
                .build();

        byte[] dados;
        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(request);
        try {
            dados = response.readAllBytes();
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Falha ao ler arquivo do S3.",
                    e
            );
        }
        arquivo.setDados(dados);
        return arquivo;
    }

    @Override
    public void deletarPorId(Integer id) {
        Arquivo arquivo = arquivoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Arquivo não encontrado."));
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(arquivo.getNomeGerado())
                .build();

        s3Client.deleteObject(request);
        arquivoRepository.deleteById(id);
    }
}

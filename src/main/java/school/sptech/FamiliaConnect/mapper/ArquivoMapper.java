package school.sptech.FamiliaConnect.mapper;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.FamiliaConnect.dto.arquivo.ArquivoResponseDto;
import school.sptech.FamiliaConnect.model.Arquivo;
import school.sptech.FamiliaConnect.model.CategoriaArquivo;

public class ArquivoMapper {

    public static ArquivoResponseDto toResponse(Arquivo entidade) {
        return new ArquivoResponseDto(
                entidade.getId(),
                entidade.getNomeOriginal(),
                entidade.getNomeGerado(),
                entidade.getMimeType(),
                entidade.getTamanho(),
                entidade.getDataUpload(),
                entidade.getCategoriaArquivo() != null ? entidade.getCategoriaArquivo().getNome() : null
        );
    }

    public static List<ArquivoResponseDto> toResponse(List<Arquivo> entidades) {
        return entidades.stream().map(ArquivoMapper::toResponse).toList();
    }

    // Usado quando o arquivo já nasce vinculado a uma categoria (ex.: foto enviada junto do cadastro de família).
    public static Arquivo toEntity(MultipartFile arquivo, CategoriaArquivo categoria) {
        Arquivo entidade = new Arquivo();
        entidade.setNomeOriginal(arquivo.getOriginalFilename());
        entidade.setMimeType(arquivo.getContentType());
        entidade.setTamanho(arquivo.getSize());
        entidade.setCategoriaArquivo(categoria);
        try {
            entidade.setDados(arquivo.getBytes());
        } catch (IOException e) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR,
                    "Falha ao ler os dados do arquivo.",
                    e
            );
        }
        return entidade;
    }

}

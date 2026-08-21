package school.sptech.FamiliaConnect.dto.arquivo;

import java.time.LocalDateTime;

public record ArquivoResponseDto(
        Integer id,
        String nomeOriginal,
        String nomeGerado,
        String contentType,
        Long tamanho,
        LocalDateTime dataUpload,
        String categoriaArquivo
) {

}

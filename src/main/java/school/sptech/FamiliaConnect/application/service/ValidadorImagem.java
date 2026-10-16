package school.sptech.FamiliaConnect.application.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.domain.exception.ImagemInvalidaException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.util.Iterator;

@Component
public class ValidadorImagem {

    private static final int  LADO_AVATAR = 512;
    private static final long TAMANHO_MAX_BYTES = 1024 * 1024;   // 1MB, igual ao front
    private static final int  LARGURA_MIN_OCR = 600;
    private static final int  LARGURA_MAX_OCR = 1800;
    private static final double PROPORCAO_OCR = 3.0 / 2.0;
    private static final double TOLERANCIA_PROPORCAO = 0.05;     // 5%

    public void validarAvatar(MultipartFile arquivo) {
        validarTamanho(arquivo);
        int[] d = lerDimensoes(arquivo);
        if (d[0] != LADO_AVATAR || d[1] != LADO_AVATAR) {
            throw new ImagemInvalidaException(
                    "A foto deve ter %dx%d pixels (recebido %dx%d).".formatted(LADO_AVATAR, LADO_AVATAR, d[0], d[1]));
        }
    }

    public void validarFormularioOcr(MultipartFile arquivo) {
        validarTamanho(arquivo);
        int[] d = lerDimensoes(arquivo);
        if (d[0] < LARGURA_MIN_OCR || d[0] > LARGURA_MAX_OCR) {
            throw new ImagemInvalidaException(
                    "A largura da imagem deve estar entre %d e %d pixels (recebido %d).".formatted(LARGURA_MIN_OCR, LARGURA_MAX_OCR, d[0]));
        }
        double proporcao = (double) d[0] / d[1];
        if (Math.abs(proporcao - PROPORCAO_OCR) / PROPORCAO_OCR > TOLERANCIA_PROPORCAO) {
            throw new ImagemInvalidaException("A imagem deve ter proporção 3:2. Recorte a ficha antes de enviar.");
        }
    }

    private void validarTamanho(MultipartFile arquivo) {
        if (arquivo.getSize() > TAMANHO_MAX_BYTES) {
            throw new ImagemInvalidaException("A imagem deve ter no máximo 1MB.");
        }
    }

    private int[] lerDimensoes(MultipartFile arquivo) {
        try (ImageInputStream iis = ImageIO.createImageInputStream(arquivo.getInputStream())) {
            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
            if (!readers.hasNext()) {
                throw new ImagemInvalidaException("O arquivo não é uma imagem válida.");
            }
            ImageReader reader = readers.next();
            try {
                reader.setInput(iis);
                return new int[]{reader.getWidth(0), reader.getHeight(0)};
            } finally {
                reader.dispose();
            }
        } catch (IOException e) {
            throw new ImagemInvalidaException("Não foi possível ler a imagem.");
        }
    }
}

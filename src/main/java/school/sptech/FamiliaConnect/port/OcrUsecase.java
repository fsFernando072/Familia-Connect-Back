package school.sptech.FamiliaConnect.port;

import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.dto.ocr.FamiliaFormResponseDto;

public interface OcrUsecase {

    FamiliaFormResponseDto extractDadosFamilia(MultipartFile file);

}

package school.sptech.FamiliaConnect.application.ports.in;

import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.infraestructure.web.dto.ocr.FamiliaFormResponseDto;

public interface OcrUseCase {

    FamiliaFormResponseDto extractDadosFamilia(MultipartFile file);

}

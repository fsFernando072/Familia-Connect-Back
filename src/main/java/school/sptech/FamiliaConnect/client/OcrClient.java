package school.sptech.FamiliaConnect.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.dto.ocr.FamiliaFormResponseDto;

@FeignClient(
        name =  "ocr-service",
        url = "${url-ocr-service}"
)
@Component
public interface OcrClient {

    @PostMapping
    FamiliaFormResponseDto getDadosFamilia(String fotoFamilia);

}

package school.sptech.FamiliaConnect.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.dto.ocr.FamiliaFormResponseDto;

import java.util.List;

@FeignClient(
        name =  "ocr-service",
        url = "${url-ocr-service}"
)
@Component
public interface OcrClient {

    @PostMapping(value = "/cesta-basica", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<FamiliaFormResponseDto> getDadosFamilia(@RequestPart("file") MultipartFile fotoFamilia);

}

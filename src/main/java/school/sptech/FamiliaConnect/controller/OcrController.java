package school.sptech.FamiliaConnect.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.dto.ocr.FamiliaFormResponseDto;
import school.sptech.FamiliaConnect.service.OcrService;

@RestController
@RequestMapping("/ocr")
@AllArgsConstructor
public class OcrController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OcrController.class);
    private final OcrService ocrService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FamiliaFormResponseDto> consumerDadosFamilia(@RequestParam MultipartFile arquivo) {
        LOGGER.info("Iniciando extração de dados da foto {}", arquivo.getName());

        return ResponseEntity.ok().body(ocrService.extractDadosFamilia(arquivo));
    }

}

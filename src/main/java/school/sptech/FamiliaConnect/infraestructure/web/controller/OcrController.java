package school.sptech.FamiliaConnect.infraestructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.application.ports.in.OcrUseCase;
import school.sptech.FamiliaConnect.infraestructure.web.dto.ocr.FamiliaFormResponseDto;
import school.sptech.FamiliaConnect.application.service.OcrService;

@Tag(name = "OCR", description = "Reconhecimento óptico de carecteres")
@RestController
@RequestMapping("/ocr")
public class OcrController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OcrController.class);
    private final OcrUseCase ocrUseCase;

    public OcrController(OcrUseCase ocrUseCase) {
        this.ocrUseCase = ocrUseCase;
    }

    @Operation(
            summary = "Chamada para API OCR",
            description = "Chama a API OCR de leitura de formulário e retorna a família."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Família retornada com sucesso"),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FamiliaFormResponseDto> consumerDadosFamilia(@RequestParam MultipartFile arquivo) {
        LOGGER.info("Iniciando extração de dados da foto {}", arquivo.getName());

        return ResponseEntity.ok().body(ocrUseCase.extractDadosFamilia(arquivo));
    }

}

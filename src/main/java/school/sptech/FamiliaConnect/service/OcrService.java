package school.sptech.FamiliaConnect.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.client.OcrClient;
import school.sptech.FamiliaConnect.dto.ocr.FamiliaFormResponseDto;
import school.sptech.FamiliaConnect.enums.TipoArquivoEnum;
import school.sptech.FamiliaConnect.exception.DadosDaFamiliaAusenteException;
import school.sptech.FamiliaConnect.exception.TipoDeArquivoIncompativelException;
import school.sptech.FamiliaConnect.port.OcrUsecase;

import java.util.List;

@Service
public class OcrService implements OcrUsecase {

    private final OcrClient ocrClient;
    private final Logger LOGGER = LoggerFactory.getLogger(OcrService.class);

    public OcrService(OcrClient ocrClient) {
        this.ocrClient = ocrClient;
    }

    public FamiliaFormResponseDto extractDadosFamilia(MultipartFile fotoFamilia) {
        validateFile(fotoFamilia);

        //todo: colocar um try-catch para tratativa de erros global
        FamiliaFormResponseDto dadosFamilia = ocrClient.getDadosFamilia(fotoFamilia).getFirst();
        LOGGER.info("Retorno recebido da API externa OCR = {}", dadosFamilia.toString());

        dataFamiliaIsNotBlank(dadosFamilia);

        return dadosFamilia;

    }

    private void validateFile(MultipartFile fotoFamilia) {
        if(fotoFamilia == null){
            throw new TipoDeArquivoIncompativelException(
                    "Erro ao identificar arquivo"
            );
        }

        LOGGER.info(
                "Iniciando validação de arquivo recebido, nome {}; tipo {}",
                fotoFamilia.getOriginalFilename(),
                fotoFamilia.getContentType()
        );

        TipoArquivoEnum.validateEnum(fotoFamilia.getContentType());
    }

    private void dataFamiliaIsNotBlank(FamiliaFormResponseDto dadosFamilia) {
        if(dadosFamilia == null || dadosFamilia.getResponsavel() == null || dadosFamilia.getFamiliaEndereco() == null) {
            throw new DadosDaFamiliaAusenteException("Erro ao obter dados da família");
        }
    }


}

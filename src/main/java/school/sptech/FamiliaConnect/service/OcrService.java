package school.sptech.FamiliaConnect.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.client.OcrClient;
import school.sptech.FamiliaConnect.dto.ocr.FamiliaFormResponseDto;
import school.sptech.FamiliaConnect.enums.TipoArquivoEnum;
import school.sptech.FamiliaConnect.exception.DadosDaFamiliaAusenteException;
import school.sptech.FamiliaConnect.exception.TipoDeArquivoIncompativelException;

@Service
@AllArgsConstructor
@Builder
public class OcrService {

    private final OcrClient ocrClient;

    public FamiliaFormResponseDto extractDadosFamilia(MultipartFile fotoFamilia) {

        validateFile(fotoFamilia);

        FamiliaFormResponseDto dadosFamilia = ocrClient.getDadosFamilia("mock");

        dataFamiliaIsNull(dadosFamilia);

        return dadosFamilia;

    }

    private void validateFile(MultipartFile fotoFamilia) {
        if(fotoFamilia == null){
            throw new TipoDeArquivoIncompativelException("Erro ao identificar arquivo");
        }

        TipoArquivoEnum.validateEnum(fotoFamilia.getContentType());
    }

    private void dataFamiliaIsNull(FamiliaFormResponseDto dadosFamilia) {
        if(dadosFamilia == null) {
            throw new DadosDaFamiliaAusenteException("Erro ao obter dados da família");
        }
    }


}

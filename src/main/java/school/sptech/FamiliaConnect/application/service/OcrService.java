package school.sptech.FamiliaConnect.application.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.infraestructure.web.client.OcrClient;
import school.sptech.FamiliaConnect.infraestructure.web.dto.ocr.FamiliaFormResponseDto;
import school.sptech.FamiliaConnect.domain.enums.TipoArquivoEnum;
import school.sptech.FamiliaConnect.domain.exception.DadosDaFamiliaAusenteException;
import school.sptech.FamiliaConnect.domain.exception.TipoDeArquivoIncompativelException;
import school.sptech.FamiliaConnect.application.ports.in.OcrUseCase;

import java.util.List;

@Service
public class OcrService implements OcrUseCase {

    private final OcrClient ocrClient;

    public OcrService(OcrClient ocrClient) {
        this.ocrClient = ocrClient;
    }

    public FamiliaFormResponseDto extractDadosFamilia(MultipartFile fotoFamilia) {
        validateFile(fotoFamilia);

        //todo: colocar um try-catch para tratativa de erros global
        List<FamiliaFormResponseDto> listaFamilia = ocrClient.getDadosFamilia(fotoFamilia);
        if (listaFamilia.isEmpty()) {
            throw new DadosDaFamiliaAusenteException("Não foi possível encontrar os dados da familia");
        }

        FamiliaFormResponseDto dadosFamilia = listaFamilia.getFirst();

        dataFamiliaIsNotBlank(dadosFamilia);

        return dadosFamilia;

    }

    private void validateFile(MultipartFile fotoFamilia) {
        if(fotoFamilia == null){
            throw new TipoDeArquivoIncompativelException(
                    "Erro ao identificar arquivo"
            );
        }

        TipoArquivoEnum.validateEnum(fotoFamilia.getContentType());
    }

    private void dataFamiliaIsNotBlank(FamiliaFormResponseDto dadosFamilia) {
        if(dadosFamilia == null || dadosFamilia.getResponsavel() == null || dadosFamilia.getFamiliaEndereco() == null) {
            throw new DadosDaFamiliaAusenteException("Erro ao obter dados da família");
        }
    }


}

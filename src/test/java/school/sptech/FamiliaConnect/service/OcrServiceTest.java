package school.sptech.FamiliaConnect.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.client.OcrClient;
import school.sptech.FamiliaConnect.dto.ocr.EnderecoResponseDto;
import school.sptech.FamiliaConnect.dto.ocr.FamiliaFormResponseDto;
import school.sptech.FamiliaConnect.dto.ocr.ResponsavelResponseDto;
import school.sptech.FamiliaConnect.enums.TipoArquivoEnum;
import school.sptech.FamiliaConnect.exception.DadosDaFamiliaAusenteException;
import school.sptech.FamiliaConnect.exception.TipoDeArquivoIncompativelException;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class OcrServiceTest {

    @Mock
    OcrClient ocrClient;

    @InjectMocks
    OcrService ocrService;

    @Nested
    @DisplayName("Deve extrair dados da familia corretamente")
    class extractDadosFamilia {

        @Test
        @DisplayName("Deve retornar dados da familia com sucesso")
        void retornarDadosDaFamilia() {
            MultipartFile arquivo = Mockito.mock(MultipartFile.class);
            Mockito.when(arquivo.getContentType()).thenReturn("image/png");

            FamiliaFormResponseDto dadosMock = new FamiliaFormResponseDto();

            dadosMock.setResponsavel(new ResponsavelResponseDto());

            dadosMock.setFamiliaEndereco(new EnderecoResponseDto());

            try (MockedStatic<TipoArquivoEnum> mockedStatic = Mockito.mockStatic(TipoArquivoEnum.class)) {
                mockedStatic.when(() -> TipoArquivoEnum.validateEnum("image/png"))
                        .thenAnswer(invocation -> null);

                Mockito.when(ocrClient.getDadosFamilia(arquivo)).thenReturn(List.of(dadosMock));

                FamiliaFormResponseDto resultado = ocrService.extractDadosFamilia(arquivo);

                Assertions.assertEquals(dadosMock, resultado);
            }
        }

        @Test
        @DisplayName("Deve lancar TipoDeArquivoIncompativelException quando arquivo for nulo")
        void retornarExceptionArquivoNulo() {
            Assertions.assertThrows(
                    TipoDeArquivoIncompativelException.class,
                    () -> ocrService.extractDadosFamilia(null)
            );
        }

        @Test
        @DisplayName("Deve lancar TipoDeArquivoIncompativelException quando tipo do arquivo for invalido")
        void retornarExceptionTipoArquivoInvalido() {
            MultipartFile arquivo = Mockito.mock(MultipartFile.class);
            Mockito.when(arquivo.getContentType()).thenReturn("application/pdf");

            Assertions.assertThrows(
                    TipoDeArquivoIncompativelException.class,
                    () -> ocrService.extractDadosFamilia(arquivo)
            );
        }

        @Test
        @DisplayName("Deve lancar DadosDaFamiliaAusenteException quando OCR retornar nulo")
        void retornarExceptionDadosDaFamiliaAusentes() {
            MultipartFile arquivo = Mockito.mock(MultipartFile.class);
            Mockito.when(arquivo.getContentType()).thenReturn("image/png");

            try (MockedStatic<TipoArquivoEnum> mockedStatic = Mockito.mockStatic(TipoArquivoEnum.class)) {
                mockedStatic.when(() -> TipoArquivoEnum.validateEnum("image/png"))
                        .thenAnswer(invocation -> null);

                FamiliaFormResponseDto dtoVazio =
                        new FamiliaFormResponseDto();

                Mockito.when(ocrClient.getDadosFamilia(arquivo))
                        .thenReturn(List.of(dtoVazio));

                Assertions.assertThrows(
                        DadosDaFamiliaAusenteException.class,
                        () -> ocrService.extractDadosFamilia(arquivo)
                );
            }
        }
    }
}


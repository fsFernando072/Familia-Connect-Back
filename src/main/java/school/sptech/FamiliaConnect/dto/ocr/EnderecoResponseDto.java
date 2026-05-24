package school.sptech.FamiliaConnect.dto.ocr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EnderecoResponseDto {

    private String cep;
    private String bairro;
    private String logradouro;
    private String complemento;
    private String cidade;

}


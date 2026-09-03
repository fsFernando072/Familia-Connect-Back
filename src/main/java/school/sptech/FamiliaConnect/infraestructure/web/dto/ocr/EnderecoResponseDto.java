package school.sptech.FamiliaConnect.infraestructure.web.dto.ocr;

import lombok.Data;

@Data
public class EnderecoResponseDto {

    private String cep;
    private String bairro;
    private String numero;
    private String logradouro;
    private String complemento;
    private String cidade;

}



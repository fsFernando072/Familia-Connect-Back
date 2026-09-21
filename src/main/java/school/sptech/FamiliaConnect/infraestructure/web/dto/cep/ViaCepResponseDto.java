package school.sptech.FamiliaConnect.infraestructure.web.dto.cep;

import lombok.Data;

/**
 * Espelha exatamente o formato de resposta da API externa do ViaCEP
 * (https://viacep.com.br/ws/{cep}/json/). Usado somente entre o {@code ViaCepClient}
 * e o {@code ViaCepAdapter} — o resto da aplicação nunca enxerga esse formato,
 * apenas o domínio {@code EnderecoCep}.
 */
@Data
public class ViaCepResponseDto {

    private String cep;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    // Vem "true" quando o CEP é válido no formato mas não existe.
    private Boolean erro;
}

package school.sptech.FamiliaConnect.infraestructure.web.dto.cep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Endereço encontrado a partir do CEP consultado")
@Data
public class EnderecoCepResponseDto {

    @Schema(description = "CEP consultado")
    private String cep;

    @Schema(description = "Logradouro (rua/avenida) do endereço")
    private String logradouro;

    @Schema(description = "Complemento do endereço, quando houver")
    private String complemento;

    @Schema(description = "Bairro do endereço")
    private String bairro;

    @Schema(description = "Cidade do endereço")
    private String localidade;

    @Schema(description = "Sigla do estado (UF) do endereço")
    private String uf;
}

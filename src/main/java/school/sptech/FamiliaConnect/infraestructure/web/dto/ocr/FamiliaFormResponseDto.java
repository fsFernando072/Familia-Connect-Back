package school.sptech.FamiliaConnect.infraestructure.web.dto.ocr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FamiliaFormResponseDto {

    private EnderecoResponseDto familiaEndereco;
    private ResponsavelResponseDto responsavel;
    private List<DependenteResponseDto> dependentes;

}
package school.sptech.FamiliaConnect.infraestructure.web.dto.familia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.sptech.FamiliaConnect.infraestructure.web.dto.carteirinha.ProcessamentoCarteirinhaResponseDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FamiliaCarteirinhaResponseDto {
    private ProcessamentoCarteirinhaResponseDto processamentoCarteirinha;
    private FamiliaResponseDto familia;
}

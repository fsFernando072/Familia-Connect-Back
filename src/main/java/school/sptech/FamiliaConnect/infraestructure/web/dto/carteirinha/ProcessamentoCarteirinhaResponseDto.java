package school.sptech.FamiliaConnect.infraestructure.web.dto.carteirinha;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProcessamentoCarteirinhaResponseDto {
    private String status;
    private UUID correlationId;
}

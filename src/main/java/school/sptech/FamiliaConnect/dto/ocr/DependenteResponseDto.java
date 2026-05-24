package school.sptech.FamiliaConnect.dto.ocr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DependenteResponseDto {

    private String nome;
    private LocalDateTime dataNascimento;
    private String grauParentesco;
    private Boolean responsavel;

}


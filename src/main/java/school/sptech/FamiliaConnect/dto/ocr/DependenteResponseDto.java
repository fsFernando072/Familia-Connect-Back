package school.sptech.FamiliaConnect.dto.ocr;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DependenteResponseDto {

    private String nome;
    private LocalDateTime dataNascimento;
    private String grauParentesco;
    private Boolean responsavel;

}



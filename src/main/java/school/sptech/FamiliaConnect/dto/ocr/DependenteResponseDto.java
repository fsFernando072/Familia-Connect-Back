package school.sptech.FamiliaConnect.dto.ocr;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DependenteResponseDto {

    private String nome;
    private LocalDate dataNascimento;
    private String grauParentesco;
    private Boolean isResponsavel;

}



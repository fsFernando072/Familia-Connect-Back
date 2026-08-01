package school.sptech.FamiliaConnect.dto.ocr;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DependenteResponseDto {

    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR")
    private LocalDate dataNascimento;
    private String grauParentesco;
    private Boolean responsavel;

}



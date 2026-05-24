package school.sptech.FamiliaConnect.dto.ocr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponsavelResponseDto {
    private String nome;
    private String rg;
    private String cpf;
    private LocalDate dataNascimento;
    private Integer telefone;
    private String profissao;
    private Boolean responsavel;

}

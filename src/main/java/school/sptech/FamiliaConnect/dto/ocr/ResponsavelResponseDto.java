package school.sptech.FamiliaConnect.dto.ocr;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponsavelResponseDto {
    private String nome;
    private String rg;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private String profissao;
    private Boolean responsavel;

}


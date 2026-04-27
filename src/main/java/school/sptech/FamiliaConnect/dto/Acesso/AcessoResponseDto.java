package school.sptech.FamiliaConnect.dto.Acesso;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados do acesso")
public class AcessoResponseDto {

    @Schema(description = "Id da tela")
    private Integer id;

    @Schema(description = "Nome da tela")
    private String nomeTela;

    public AcessoResponseDto() {}

    public AcessoResponseDto(String nomeTela, Integer id) {
        this.nomeTela = nomeTela;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeTela() {
        return nomeTela;
    }

    public void setNomeTela(String nomeTela) {
        this.nomeTela = nomeTela;
    }
}

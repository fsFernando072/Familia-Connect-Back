package school.sptech.FamiliaConnect.dto.Estado;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados do estado")
public class EstadoResponseDto {

    @Schema(description = "ID do estado")
    private Integer id;

    @Schema(description = "Nome do estado")
    private String nome;

    @Schema(description = "Sigla do estado")
    private String sigla;

    public EstadoResponseDto() {}

    public EstadoResponseDto(Integer id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}

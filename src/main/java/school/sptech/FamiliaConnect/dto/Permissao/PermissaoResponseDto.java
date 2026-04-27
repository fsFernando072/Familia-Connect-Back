package school.sptech.FamiliaConnect.dto.Permissao;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados da permissão")
public class PermissaoResponseDto {

    @Schema(description = "ID da permissão")
    private Integer id;

    @Schema(description = "Nome da permissão")
    private String nome;

    public PermissaoResponseDto() {}

    public PermissaoResponseDto(String nome, Integer id) {
        this.nome = nome;
        this.id = id;
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
}

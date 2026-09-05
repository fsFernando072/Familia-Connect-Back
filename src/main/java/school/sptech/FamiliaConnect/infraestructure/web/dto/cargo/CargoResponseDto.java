package school.sptech.FamiliaConnect.infraestructure.web.dto.cargo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados do cargo")
public class CargoResponseDto {

    @Schema(description = "Id do cargo")
    private Integer id;

    @Schema(description = "Nome do cargo")
    private String nome;

    @Schema(description = "Descrição do cargo")
    private String descricao;

    public CargoResponseDto() {}

    public CargoResponseDto(String nome, Integer id) {
        this.nome = nome;
        this.id = id;
    }

    public CargoResponseDto(String nome, Integer id, String descricao) {
        this.nome = nome;
        this.id = id;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}

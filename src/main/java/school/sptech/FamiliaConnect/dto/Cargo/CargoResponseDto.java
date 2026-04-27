package school.sptech.FamiliaConnect.dto.Cargo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados do cargo")
public class CargoResponseDto {

    @Schema(description = "Id do cargo")
    private Integer id;

    @Schema(description = "Nome do cargo")
    private String nome;

    public CargoResponseDto() {}

    public CargoResponseDto(String nome, Integer id) {
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

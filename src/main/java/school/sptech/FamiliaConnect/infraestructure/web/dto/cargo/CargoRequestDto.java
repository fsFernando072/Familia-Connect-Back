package school.sptech.FamiliaConnect.infraestructure.web.dto.cargo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class CargoRequestDto {

    @Schema(description = "Nome do cargo")
    @NotBlank(message = "Nome do cargo é obrigatório")
    private String nome;

    @Schema(description = "Descrição do cargo")
    private String descricao;

    public CargoRequestDto() {}

    public CargoRequestDto(String nome) {
        this.nome = nome;
    }

    public CargoRequestDto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
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

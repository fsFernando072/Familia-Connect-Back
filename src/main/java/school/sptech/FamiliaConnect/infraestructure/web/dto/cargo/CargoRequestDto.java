package school.sptech.FamiliaConnect.infraestructure.web.dto.cargo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CargoRequestDto {

    @Schema(description = "Nome do cargo")
    @NotBlank(message = "Nome do cargo é obrigatório")
    private String nome;

    @Schema(description = "Descrição do cargo")
    private String descricao;

    @Schema(description = "Páginas que o cargo acessa e o nível em cada uma (lista vazia = nenhum acesso)")
    @NotNull(message = "As permissões do cargo são obrigatórias")
    @Valid
    private List<CargoPermissaoDto> permissoes;

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

    public List<CargoPermissaoDto> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<CargoPermissaoDto> permissoes) {
        this.permissoes = permissoes;
    }

}

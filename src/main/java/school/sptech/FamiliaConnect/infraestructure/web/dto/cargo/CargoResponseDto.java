package school.sptech.FamiliaConnect.infraestructure.web.dto.cargo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Dados retornados do cargo")
public class CargoResponseDto {

    @Schema(description = "Id do cargo")
    private Integer id;

    @Schema(description = "Nome do cargo")
    private String nome;

    @Schema(description = "Descrição do cargo")
    private String descricao;

    @Schema(description = "Páginas que o cargo acessa e o nível em cada uma")
    private List<CargoPermissaoDto> permissoes;

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
    

    public List<CargoPermissaoDto> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<CargoPermissaoDto> permissoes) {
        this.permissoes = permissoes;
    }

}

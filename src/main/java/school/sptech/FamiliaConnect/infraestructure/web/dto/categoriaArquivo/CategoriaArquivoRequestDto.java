package school.sptech.FamiliaConnect.infraestructure.web.dto.categoriaArquivo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class CategoriaArquivoRequestDto {

    @Schema(description = "Nome/código da categoria de arquivo (ex.: familias, funcionarios)")
    @NotBlank(message = "Nome da categoria de arquivo é obrigatório")
    private String nome;

    public CategoriaArquivoRequestDto() {
    }

    public CategoriaArquivoRequestDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

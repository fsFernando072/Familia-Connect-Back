package school.sptech.FamiliaConnect.infraestructure.web.dto.categoriaArquivo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaArquivoRequestDto {

    @Schema(description = "Nome/código da categoria de arquivo (ex.: familias, funcionarios)")
    @NotBlank(message = "Nome da categoria de arquivo é obrigatório")
    @Size(min = 3, max = 100, message = "O tamanho da categoria de arquivo deve estar entre 3 e 100")
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

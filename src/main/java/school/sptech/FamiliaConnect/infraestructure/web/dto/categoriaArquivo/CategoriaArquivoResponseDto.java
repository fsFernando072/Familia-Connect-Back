package school.sptech.FamiliaConnect.infraestructure.web.dto.categoriaArquivo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados da categoria de arquivo")
public class CategoriaArquivoResponseDto {

    @Schema(description = "ID da categoria de arquivo")
    private Integer id;

    @Schema(description = "Nome/código da categoria de arquivo")
    private String nome;

    public CategoriaArquivoResponseDto() {
    }

    public CategoriaArquivoResponseDto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

package school.sptech.FamiliaConnect.dto.Permissao;

public class PermissaoResponseDto {

    private Integer id;
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

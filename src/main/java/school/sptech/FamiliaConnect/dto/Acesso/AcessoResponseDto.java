package school.sptech.FamiliaConnect.dto.Acesso;

public class AcessoResponseDto {

    private Integer id;
    private String nomeTela;

    public AcessoResponseDto() {}

    public AcessoResponseDto(String nomeTela, Integer id) {
        this.nomeTela = nomeTela;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeTela() {
        return nomeTela;
    }

    public void setNomeTela(String nomeTela) {
        this.nomeTela = nomeTela;
    }
}

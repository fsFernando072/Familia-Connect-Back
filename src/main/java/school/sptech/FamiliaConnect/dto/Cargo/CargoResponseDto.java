package school.sptech.FamiliaConnect.dto.Cargo;

public class CargoResponseDto {

    private Integer id;
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

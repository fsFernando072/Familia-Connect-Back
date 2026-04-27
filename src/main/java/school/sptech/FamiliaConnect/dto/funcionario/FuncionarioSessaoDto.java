package school.sptech.FamiliaConnect.dto.funcionario;

public class FuncionarioSessaoDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private Integer id;
    private String nome;
    private String cpf;

    // Getters e Setters -----------------------------------------------------------------------------------------------

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

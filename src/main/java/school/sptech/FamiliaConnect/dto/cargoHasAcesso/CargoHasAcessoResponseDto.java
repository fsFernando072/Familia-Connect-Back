package school.sptech.FamiliaConnect.dto.cargoHasAcesso;

public class CargoHasAcessoResponseDto {

    private Integer id;
    private Cargo cargo;
    private Acesso acesso;

    public static class Cargo {
        private Integer id;
        private String nome;

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

    public static class Acesso {
        private Integer id;
        private String nomeTela;

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

    public CargoHasAcessoResponseDto() {}

    public CargoHasAcessoResponseDto(Integer id, Cargo cargo, Acesso acesso) {
        this.id = id;
        this.cargo = cargo;
        this.acesso = acesso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }

}

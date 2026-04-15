package school.sptech.FamiliaConnect.dto.CargoHasAcesso;

public class CargoHasAcessoResponseDto {

    private Integer id;
    private Cargo cargo;
    private Acesso acesso;
    private Permissao permissao;

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

    public static class Permissao {
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

    public CargoHasAcessoResponseDto() {}

    public CargoHasAcessoResponseDto(Integer id, Cargo cargo, Acesso acesso, Permissao permissao) {
        this.id = id;
        this.cargo = cargo;
        this.acesso = acesso;
        this.permissao = permissao;
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

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }
}

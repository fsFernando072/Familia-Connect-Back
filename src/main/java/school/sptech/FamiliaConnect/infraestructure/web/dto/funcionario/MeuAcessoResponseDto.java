package school.sptech.FamiliaConnect.infraestructure.web.dto.funcionario;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.FamiliaConnect.infraestructure.web.dto.cargo.CargoPermissaoDto;

import java.util.List;

@Schema(description = "Dados do usuário logado e as permissões herdadas do cargo")
public class MeuAcessoResponseDto {

    private String nome;
    private String cpf;
    private List<CargoPermissaoDto> permissoes;

    public MeuAcessoResponseDto() {}

    public MeuAcessoResponseDto(String nome, String cpf, List<CargoPermissaoDto> permissoes) {
        this.nome = nome;
        this.cpf = cpf;
        this.permissoes = permissoes;
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

    public List<CargoPermissaoDto> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<CargoPermissaoDto> permissoes) {
        this.permissoes = permissoes;
    }
}

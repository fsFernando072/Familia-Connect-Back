package school.sptech.FamiliaConnect.application.ports.in;

import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.domain.entity.Funcionario;
import school.sptech.FamiliaConnect.infraestructure.web.dto.funcionario.FuncionarioTokenDto;

import java.util.List;

public interface FuncionarioUseCase {

    List<Funcionario> listar();
    Funcionario listarPorId(Integer id);
    Funcionario salvar(Funcionario funcionario, MultipartFile foto);
    Funcionario atualizar(Integer id, Funcionario funcionario, MultipartFile foto);
    void deletar(Integer id);
    FuncionarioTokenDto autenticar(Funcionario usuario);

}

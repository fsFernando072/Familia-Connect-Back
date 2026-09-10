package school.sptech.FamiliaConnect.application.ports.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.domain.entity.Funcionario;
import school.sptech.FamiliaConnect.infraestructure.web.dto.funcionario.FuncionarioTokenDto;

public interface FuncionarioUseCase {

    Page<Funcionario> listar(String nome, Pageable pageable);
    Funcionario listarPorId(Integer id);
    Funcionario salvar(Funcionario funcionario, MultipartFile foto);
    Funcionario atualizar(Integer id, Funcionario funcionario, MultipartFile foto);
    void deletar(Integer id);
    FuncionarioTokenDto autenticar(Funcionario usuario);

}

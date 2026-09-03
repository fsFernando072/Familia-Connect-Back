package school.sptech.FamiliaConnect.application.ports.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.FamiliaConnect.domain.entity.Familia;
import school.sptech.FamiliaConnect.domain.entity.Pessoa;
import school.sptech.FamiliaConnect.infraestructure.web.dto.familia.FamiliaListResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.familia.FamiliaRequestDto;

import java.util.List;

public interface FamiliaUseCase {

    Familia salvar(FamiliaRequestDto dto, MultipartFile foto);
    Page<FamiliaListResponseDto> listar(Pageable pageable);
    Familia listarPorId(Integer id);
    List<Pessoa> listarIntegrantes(Integer id);
    Familia atualizar(Integer idFamilia, FamiliaRequestDto dto, MultipartFile foto);
    void deletar(Integer id);
}

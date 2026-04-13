package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.Permissao.PermissaoRequestDto;
import school.sptech.FamiliaConnect.mapper.PermissaoMapper;
import school.sptech.FamiliaConnect.model.Permissao;
import school.sptech.FamiliaConnect.repository.PermissaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PermissaoService {

    private final PermissaoRepository permissaoRepository;

    public PermissaoService(PermissaoRepository permissaoRepository) {
        this.permissaoRepository = permissaoRepository;
    }

    public Permissao cadastrar(PermissaoRequestDto dto) {
        Permissao permissao = PermissaoMapper.toModel(dto);
        return permissaoRepository.save(permissao);
    }

    public List<Permissao> listar() {
        return permissaoRepository.findAll();
    }

    public Optional<Permissao> buscarPorId(Integer id) {
        return permissaoRepository.findById(id);
    }

    public Optional<Permissao> atualizar(Integer id, PermissaoRequestDto dto) {
        Optional<Permissao> permissaoOpt = permissaoRepository.findById(id);

        if (permissaoOpt.isEmpty()) {
            return Optional.empty();
        }

        Permissao permissao = permissaoOpt.get();
        permissao.setNome(dto.getNome());

        return Optional.of(permissaoRepository.save(permissao));
    }

    public boolean deletar(Integer id) {
        if (!permissaoRepository.existsById(id)) {
            return false;
        }

        permissaoRepository.deleteById(id);
        return true;
    }

}

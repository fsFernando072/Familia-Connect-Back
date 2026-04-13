package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.Acesso.AcessoRequestDto;
import school.sptech.FamiliaConnect.mapper.AcessoMapper;
import school.sptech.FamiliaConnect.model.Acesso;
import school.sptech.FamiliaConnect.repository.AcessoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AcessoService {

    private final AcessoRepository acessoRepository;

    public AcessoService(AcessoRepository acessoRepository) {
        this.acessoRepository = acessoRepository;
    }

    public Acesso cadastrar(AcessoRequestDto dto) {
        Acesso acesso = AcessoMapper.toModel(dto);
        return acessoRepository.save(acesso);
    }

    public List<Acesso> listar() {
        return acessoRepository.findAll();
    }

    public Optional<Acesso> buscarPorId(Integer id) {
        return acessoRepository.findById(id);
    }

    public Optional<Acesso> atualizar(Integer id, AcessoRequestDto dto) {
        Optional<Acesso> acessoOpt = acessoRepository.findById(id);

        if (acessoOpt.isEmpty()) {
            return Optional.empty();
        }

        Acesso acesso = acessoOpt.get();
        acesso.setNomeTela(dto.getNomeTela());

        return Optional.of(acessoRepository.save(acesso));
    }

    public boolean deletar(Integer id) {
        if (!acessoRepository.existsById(id)) {
            return false;
        }

        acessoRepository.deleteById(id);
        return true;
    }
}
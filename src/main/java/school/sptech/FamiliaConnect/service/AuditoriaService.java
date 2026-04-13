package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.Auditoria.AuditoriaRequestDto;
import school.sptech.FamiliaConnect.mapper.AuditoriaMapper;
import school.sptech.FamiliaConnect.model.Auditoria;
import school.sptech.FamiliaConnect.model.Funcionario;
import school.sptech.FamiliaConnect.repository.AuditoriaRepository;
import school.sptech.FamiliaConnect.repository.FuncionarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;
    private final FuncionarioRepository funcionarioRepository;

    public AuditoriaService(AuditoriaRepository auditoriaRepository,
                            FuncionarioRepository funcionarioRepository) {
        this.auditoriaRepository = auditoriaRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public Optional<Auditoria> cadastrar(AuditoriaRequestDto dto) {
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(dto.getFuncionarioId());

        if (funcionarioOpt.isEmpty()) {
            return Optional.empty();
        }

        Auditoria auditoria = AuditoriaMapper.toModel(dto, funcionarioOpt.get());
        return Optional.of(auditoriaRepository.save(auditoria));
    }

    public List<Auditoria> listar() {
        return auditoriaRepository.findAll();
    }

    public Optional<Auditoria> buscarPorId(Integer id) {
        return auditoriaRepository.findById(id);
    }

    public Optional<Auditoria> atualizar(Integer id, AuditoriaRequestDto dto) {
        Optional<Auditoria> auditoriaOpt = auditoriaRepository.findById(id);

        if (auditoriaOpt.isEmpty()) {
            return Optional.empty();
        }

        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(dto.getFuncionarioId());

        if (funcionarioOpt.isEmpty()) {
            return Optional.empty();
        }

        Auditoria auditoria = auditoriaOpt.get();
        auditoria.setTipoLog(dto.getTipoLog());
        auditoria.setDadoAntigo(dto.getAcaoAntigo());
        auditoria.setDadoNovo(dto.getAcaoNovo());
        auditoria.setCreatedAt(dto.getCreatedAt());
        auditoria.setFuncionario(funcionarioOpt.get());

        return Optional.of(auditoriaRepository.save(auditoria));
    }

    public boolean deletar(Integer id) {
        if (!auditoriaRepository.existsById(id)) {
            return false;
        }

        auditoriaRepository.deleteById(id);
        return true;
    }
}

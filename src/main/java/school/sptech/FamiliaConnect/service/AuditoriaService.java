package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.Auditoria.AuditoriaRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.AuditoriaMapper;
import school.sptech.FamiliaConnect.model.Auditoria;
import school.sptech.FamiliaConnect.model.Funcionario;
import school.sptech.FamiliaConnect.repository.AuditoriaRepository;
import school.sptech.FamiliaConnect.repository.FuncionarioRepository;

import java.util.List;

@Service
public class AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;
    private final FuncionarioRepository funcionarioRepository;

    public AuditoriaService(AuditoriaRepository auditoriaRepository,
                            FuncionarioRepository funcionarioRepository) {
        this.auditoriaRepository = auditoriaRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public Auditoria cadastrar(AuditoriaRequestDto dto) {
        Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionarioId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O funcionário com o id não foi encontrada"));

        Auditoria auditoria = AuditoriaMapper.toModel(dto);
        auditoria.setFuncionario(funcionario);

        return auditoriaRepository.save(auditoria);
    }

    public List<Auditoria> listar() {
        return auditoriaRepository.findAll();
    }

    public Auditoria buscarPorId(Integer id) {
        return auditoriaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("A auditoria com o id não foi encontrada"));
    }

    public Auditoria atualizar(Integer id, AuditoriaRequestDto dto) {
        if (!auditoriaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("A auditoria com o id não foi encontrada");
        }

        Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionarioId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O funcionário com o id não foi encontrada"));

        Auditoria auditoria = AuditoriaMapper.toModel(dto);
        auditoria.setId(id);
        auditoria.setFuncionario(funcionario);

        return auditoriaRepository.save(auditoria);
    }

    public void deletar(Integer id) {
        if (!auditoriaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("A auditoria com o id não foi encontrada");
        }

        auditoriaRepository.deleteById(id);
    }
}

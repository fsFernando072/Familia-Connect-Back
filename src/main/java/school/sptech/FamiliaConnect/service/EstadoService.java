package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.Estado.EstadoRequestDto;
import school.sptech.FamiliaConnect.mapper.EstadoMapper;
import school.sptech.FamiliaConnect.model.Estado;
import school.sptech.FamiliaConnect.repository.EstadoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public Estado cadastrar(EstadoRequestDto dto) {
        Estado estado = EstadoMapper.toModel(dto);
        return estadoRepository.save(estado);
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Optional<Estado> buscarPorId(Integer id) {
        return estadoRepository.findById(id);
    }

    public Optional<Estado> atualizar(Integer id, EstadoRequestDto dto) {
        Optional<Estado> estadoOpt = estadoRepository.findById(id);

        if (estadoOpt.isEmpty()) {
            return Optional.empty();
        }

        Estado estado = estadoOpt.get();
        estado.setNome(dto.getNome());
        estado.setSigla(dto.getSigla());

        return Optional.of(estadoRepository.save(estado));
    }

    public boolean deletar(Integer id) {
        if (!estadoRepository.existsById(id)) {
            return false;
        }

        estadoRepository.deleteById(id);
        return true;
    }
}
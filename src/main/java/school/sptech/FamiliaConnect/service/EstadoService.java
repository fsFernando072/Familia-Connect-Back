package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.Estado.EstadoRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
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

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscarPorId(Integer id) {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O estado com o id não foi encontrado"));
    }
}
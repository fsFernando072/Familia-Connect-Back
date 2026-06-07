package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.Cargo.CargoRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.CargoMapper;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.repository.CargoRepository;

import java.util.List;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public Cargo cadastrar(Cargo cargo) {
        cargoRepository.findByNome(cargo.getNome())
                .ifPresent(cargo1 -> {
                    throw new EntidadeJaCadastradaException("Cargo já cadastrado");
                });
        return cargoRepository.save(cargo);
    }

    public List<Cargo> listar() {
        return cargoRepository.findAll();
    }

    public Cargo buscarPorId(Integer id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O cargo com o id não foi encontrado"));
    }

    public Cargo atualizar(Integer id, Cargo cargo) {
        if (!cargoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("O cargo com o id não foi encontrado");
        }

        cargoRepository.findByNome(cargo.getNome())
                .ifPresent(cargo1 -> {
                    throw new EntidadeJaCadastradaException("Cargo já cadastrado");
                });

        cargo.setId(id);

        return cargoRepository.save(cargo);
    }

    public void deletar(Integer id) {
        if (!cargoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("O cargo com o id não foi encontrado");
        }

        cargoRepository.deleteById(id);
    }
}

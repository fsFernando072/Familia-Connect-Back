package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.Cargo.CargoRequestDto;
import school.sptech.FamiliaConnect.mapper.CargoMapper;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.repository.CargoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public Cargo cadastrar(CargoRequestDto dto) {
        Cargo cargo = CargoMapper.toModel(dto);
        return cargoRepository.save(cargo);
    }

    public List<Cargo> listar() {
        return cargoRepository.findAll();
    }

    public Optional<Cargo> buscarPorId(Integer id) {
        return cargoRepository.findById(id);
    }

    public Optional<Cargo> atualizar(Integer id, CargoRequestDto dto) {
        Optional<Cargo> cargoOpt = cargoRepository.findById(id);

        if (cargoOpt.isEmpty()) {
            return Optional.empty();
        }

        Cargo cargo = cargoOpt.get();
        cargo.setNome(dto.getNome());

        return Optional.of(cargoRepository.save(cargo));
    }

    public boolean deletar(Integer id) {
        if (!cargoRepository.existsById(id)) {
            return false;
        }

        cargoRepository.deleteById(id);
        return true;
    }
}

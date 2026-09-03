package school.sptech.FamiliaConnect.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.FamiliaConnect.application.ports.in.CargoUseCase;
import school.sptech.FamiliaConnect.domain.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.domain.entity.Cargo;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.CargoHasAcessoRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.CargoRepository;

import java.util.List;

@Service
public class CargoService implements CargoUseCase {

    private final CargoRepository cargoRepository;
    private final CargoHasAcessoService cargoHasAcessoService;

    public CargoService(CargoRepository cargoRepository, CargoHasAcessoService cargoHasAcessoService) {
        this.cargoRepository = cargoRepository;
        this.cargoHasAcessoService = cargoHasAcessoService;
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

        cargoRepository.findByNomeAndIdNot(cargo.getNome(), id)
                .ifPresent(cargo1 -> {
                    throw new EntidadeJaCadastradaException("Cargo já cadastrado");
                });

        cargo.setId(id);

        return cargoRepository.save(cargo);
    }

    @Transactional
    public void deletar(Integer id) {
        if (!cargoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("O cargo com o id não foi encontrado");
        }

        cargoHasAcessoService.deletarPorCargoId(id);
        cargoRepository.deleteById(id);
    }
}

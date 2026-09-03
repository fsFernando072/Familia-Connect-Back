package school.sptech.FamiliaConnect.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.FamiliaConnect.application.ports.in.CargoHasAcessoUseCase;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.domain.entity.Acesso;
import school.sptech.FamiliaConnect.domain.entity.Cargo;
import school.sptech.FamiliaConnect.domain.entity.CargoHasAcesso;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.AcessoRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.CargoHasAcessoRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.CargoRepository;

import java.util.List;

@Service
public class CargoHasAcessoService implements CargoHasAcessoUseCase {

    private final CargoHasAcessoRepository cargoHasAcessoRepository;
    private final CargoRepository cargoRepository;
    private final AcessoRepository acessoRepository;

    public CargoHasAcessoService(CargoHasAcessoRepository cargoHasAcessoRepository,
                                 CargoRepository cargoRepository,
                                 AcessoRepository acessoRepository) {
        this.cargoHasAcessoRepository = cargoHasAcessoRepository;
        this.cargoRepository = cargoRepository;
        this.acessoRepository = acessoRepository;
    }

    public CargoHasAcesso cadastrar(CargoHasAcesso cargoHasAcesso) {

        Integer cargoId = cargoHasAcesso.getCargo().getId();
        Integer acessoId = cargoHasAcesso.getAcesso().getId();

        Cargo cargo = cargoRepository.findById(cargoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O cargo com o id não foi encontrado"));
        Acesso acesso = acessoRepository.findById(acessoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O acesso com o id não foi encontrado"));


        cargoHasAcesso.setCargo(cargo);
        cargoHasAcesso.setAcesso(acesso);


        return cargoHasAcessoRepository.save(cargoHasAcesso);
    }

    public List<CargoHasAcesso> listar() {
        return cargoHasAcessoRepository.findAll();
    }

    public CargoHasAcesso buscarPorId(Integer id) {
        return cargoHasAcessoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O cargo com id não foi encontrado"));
    }

    public CargoHasAcesso atualizar(Integer id, CargoHasAcesso cargoHasAcesso) {
        if (!cargoHasAcessoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("O CargoHasAcesso com id não foi encontrado");
        }
        Integer cargoId = cargoHasAcesso.getCargo().getId();
        Integer acessoId = cargoHasAcesso.getAcesso().getId();

        Cargo cargo = cargoRepository.findById(cargoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O cargo com id não foi encontrado"));
        Acesso acesso = acessoRepository.findById(acessoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O acesso com id não foi encontrado"));

        cargoHasAcesso.setId(id);
        cargoHasAcesso.setCargo(cargo);
        cargoHasAcesso.setAcesso(acesso);

        return cargoHasAcessoRepository.save(cargoHasAcesso);
    }

    public void deletar(Integer id) {
        if (!cargoHasAcessoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("O CargoHasAcesso com id não foi encontrado");
        }

        cargoHasAcessoRepository.deleteById(id);
    }

    public void deletarPorCargoId(Integer cargoId) {
        if (cargoHasAcessoRepository.existsByCargoId(cargoId)) {
            cargoHasAcessoRepository.deleteByCargoId(cargoId);
        }
    }

}

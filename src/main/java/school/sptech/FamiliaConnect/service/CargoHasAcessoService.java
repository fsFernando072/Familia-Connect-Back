package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.CargoHasAcesso.CargoHasAcessoRequestDto;
import school.sptech.FamiliaConnect.mapper.CargoHasAcessoMapper;
import school.sptech.FamiliaConnect.model.Acesso;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.model.CargoHasAcesso;
import school.sptech.FamiliaConnect.model.Permissao;
import school.sptech.FamiliaConnect.repository.AcessoRepository;
import school.sptech.FamiliaConnect.repository.CargoHasAcessoRepository;
import school.sptech.FamiliaConnect.repository.CargoRepository;
import school.sptech.FamiliaConnect.repository.PermissaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CargoHasAcessoService {

    private final CargoHasAcessoRepository cargoHasAcessoRepository;
    private final CargoRepository cargoRepository;
    private final AcessoRepository acessoRepository;
    private final PermissaoRepository permissaoRepository;

    public CargoHasAcessoService(CargoHasAcessoRepository cargoHasAcessoRepository,
                                 CargoRepository cargoRepository,
                                 AcessoRepository acessoRepository,
                                 PermissaoRepository permissaoRepository) {
        this.cargoHasAcessoRepository = cargoHasAcessoRepository;
        this.cargoRepository = cargoRepository;
        this.acessoRepository = acessoRepository;
        this.permissaoRepository = permissaoRepository;
    }

    public Optional<CargoHasAcesso> cadastrar(CargoHasAcessoRequestDto dto) {
        Optional<Cargo> cargoOpt = cargoRepository.findById(dto.getCargoId());
        Optional<Acesso> acessoOpt = acessoRepository.findById(dto.getAcessoId());
        Optional<Permissao> permissaoOpt = permissaoRepository.findById(dto.getPermissaoId());

        if (cargoOpt.isEmpty() || acessoOpt.isEmpty() || permissaoOpt.isEmpty()) {
            return Optional.empty();
        }

        CargoHasAcesso cargoHasAcesso = CargoHasAcessoMapper.toModel(
                cargoOpt.get(),
                acessoOpt.get(),
                permissaoOpt.get()
        );

        return Optional.of(cargoHasAcessoRepository.save(cargoHasAcesso));
    }

    public List<CargoHasAcesso> listar() {
        return cargoHasAcessoRepository.findAll();
    }

    public Optional<CargoHasAcesso> buscarPorId(Integer id) {
        return cargoHasAcessoRepository.findById(id);
    }

    public Optional<CargoHasAcesso> atualizar(Integer id, CargoHasAcessoRequestDto dto) {
        Optional<CargoHasAcesso> cargoHasAcessoOpt = cargoHasAcessoRepository.findById(id);

        if (cargoHasAcessoOpt.isEmpty()) {
            return Optional.empty();
        }

        Optional<Cargo> cargoOpt = cargoRepository.findById(dto.getCargoId());
        Optional<Acesso> acessoOpt = acessoRepository.findById(dto.getAcessoId());
        Optional<Permissao> permissaoOpt = permissaoRepository.findById(dto.getPermissaoId());

        if (cargoOpt.isEmpty() || acessoOpt.isEmpty() || permissaoOpt.isEmpty()) {
            return Optional.empty();
        }

        CargoHasAcesso cargoHasAcesso = cargoHasAcessoOpt.get();
        cargoHasAcesso.setCargo(cargoOpt.get());
        cargoHasAcesso.setAcesso(acessoOpt.get());
        cargoHasAcesso.setPermissao(permissaoOpt.get());

        return Optional.of(cargoHasAcessoRepository.save(cargoHasAcesso));
    }

    public boolean deletar(Integer id) {
        if (!cargoHasAcessoRepository.existsById(id)) {
            return false;
        }

        cargoHasAcessoRepository.deleteById(id);
        return true;
    }

}

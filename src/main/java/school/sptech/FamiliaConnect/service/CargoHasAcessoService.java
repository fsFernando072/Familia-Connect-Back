package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.CargoHasAcesso.CargoHasAcessoRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
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

    public CargoHasAcesso cadastrar(CargoHasAcessoRequestDto dto) {
        Cargo cargo = cargoRepository.findById(dto.getCargoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O cargo com o id não foi encontrado"));
        Acesso acesso = acessoRepository.findById(dto.getAcessoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O acesso com o id não foi encontrado"));;
        Permissao permissao = permissaoRepository.findById(dto.getPermissaoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("A permissão com o id não foi encontrada"));

        CargoHasAcesso cargoHasAcesso = CargoHasAcessoMapper.toModel(
                cargo,
                acesso,
                permissao
        );

        return cargoHasAcessoRepository.save(cargoHasAcesso);
    }

    public List<CargoHasAcesso> listar() {
        return cargoHasAcessoRepository.findAll();
    }

    public CargoHasAcesso buscarPorId(Integer id) {
        return cargoHasAcessoRepository.findById(id)
                .orElseThrow();
    }

    public CargoHasAcesso atualizar(Integer id, CargoHasAcessoRequestDto dto) {
        if (!cargoHasAcessoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("O CargoHasAcesso com id não foi encontrado");
        }

        Cargo cargo = cargoRepository.findById(dto.getCargoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O cargo com id não foi encontrado"));
        Acesso acesso = acessoRepository.findById(dto.getAcessoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O acesso com id não foi encontrado"));
        Permissao permissao = permissaoRepository.findById(dto.getPermissaoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("A permissão com id não foi encontrada"));

        CargoHasAcesso cargoHasAcesso = new CargoHasAcesso();
        cargoHasAcesso.setId(id);
        cargoHasAcesso.setCargo(cargo);
        cargoHasAcesso.setAcesso(acesso);
        cargoHasAcesso.setPermissao(permissao);

        return cargoHasAcessoRepository.save(cargoHasAcesso);
    }

    public void deletar(Integer id) {
        if (!cargoHasAcessoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("O CargoHasAcesso com id não foi encontrado");
        }

        cargoHasAcessoRepository.deleteById(id);
    }

}

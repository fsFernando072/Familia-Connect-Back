package school.sptech.FamiliaConnect.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.FamiliaConnect.application.ports.in.CargoUseCase;
import school.sptech.FamiliaConnect.domain.entity.Cargo;
import school.sptech.FamiliaConnect.domain.entity.CargoPermissao;
import school.sptech.FamiliaConnect.domain.enums.NivelAcessoEnum;
import school.sptech.FamiliaConnect.domain.enums.PaginaEnum;
import school.sptech.FamiliaConnect.domain.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.CargoRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CargoService implements CargoUseCase {

    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @Transactional
    public Cargo cadastrar(Cargo cargo) {
        cargoRepository.findByNome(cargo.getNome())
                .ifPresent(cargo1 -> {
                    throw new EntidadeJaCadastradaException("Cargo já cadastrado");
                });

        // Se a mesma página vier repetida, vale a última. As permissões são salvas junto com o cargo (cascade).
        List<CargoPermissao> permissoes = new ArrayList<>();
        mapaDePermissoes(cargo.getPermissoes())
                .forEach((pagina, nivel) -> permissoes.add(new CargoPermissao(cargo, pagina, nivel)));
        cargo.setPermissoes(permissoes);

        return cargoRepository.save(cargo);
    }

    public Page<Cargo> listar(String nome, Pageable pageable) {
        String termoPesquisa = nome != null ? nome : "";

        return cargoRepository.findByNomeContainingIgnoreCase(termoPesquisa, pageable);
    }

    public Cargo buscarPorId(Integer id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O cargo com o id não foi encontrado"));
    }

    @Transactional
    public Cargo atualizar(Integer id, Cargo cargo) {
        Cargo existente = cargoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O cargo com o id não foi encontrado"));

        cargoRepository.findByNomeAndIdNot(cargo.getNome(), id)
                .ifPresent(cargo1 -> {
                    throw new EntidadeJaCadastradaException("Cargo já cadastrado");
                });

        existente.setNome(cargo.getNome());
        existente.setDescricao(cargo.getDescricao());
        sincronizarPermissoes(existente, cargo.getPermissoes());

        return cargoRepository.save(existente);
    }

    @Transactional
    public void deletar(Integer id) {
        if (!cargoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("O cargo com o id não foi encontrado");
        }

        // As linhas de cargo_permissao do cargo são apagadas junto (cascade na entidade Cargo).
        cargoRepository.deleteById(id);
    }

    // Métodos auxiliares ----------------------------------------------------------------------------------------------

    private Map<PaginaEnum, NivelAcessoEnum> mapaDePermissoes(List<CargoPermissao> permissoes) {
        Map<PaginaEnum, NivelAcessoEnum> mapa = new LinkedHashMap<>();

        if (permissoes != null) {
            for (CargoPermissao permissao : permissoes) {
                mapa.put(permissao.getPagina(), permissao.getNivel());
            }
        }

        return mapa;
    }

    // Deixa a lista do cargo igual à enviada: muda o nível das páginas que já existem, remove as que saíram
    // e inclui as novas (a unique cargo+página não permite apagar e reinserir a mesma página no mesmo flush).
    private void sincronizarPermissoes(Cargo cargo, List<CargoPermissao> novas) {
        Map<PaginaEnum, NivelAcessoEnum> desejadas = mapaDePermissoes(novas);

        cargo.getPermissoes().removeIf(atual -> !desejadas.containsKey(atual.getPagina()));

        Set<PaginaEnum> jaExistentes = new HashSet<>();
        for (CargoPermissao atual : cargo.getPermissoes()) {
            atual.setNivel(desejadas.get(atual.getPagina()));
            jaExistentes.add(atual.getPagina());
        }

        desejadas.forEach((pagina, nivel) -> {
            if (!jaExistentes.contains(pagina)) {
                cargo.getPermissoes().add(new CargoPermissao(cargo, pagina, nivel));
            }
        });
    }
}

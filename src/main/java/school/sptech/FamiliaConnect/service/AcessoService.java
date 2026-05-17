package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.Acesso.AcessoRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.AcessoMapper;
import school.sptech.FamiliaConnect.model.Acesso;
import school.sptech.FamiliaConnect.repository.AcessoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AcessoService {

    private final AcessoRepository acessoRepository;

    public AcessoService(AcessoRepository acessoRepository) {
        this.acessoRepository = acessoRepository;
    }

    public Acesso cadastrar(AcessoRequestDto dto) {

        acessoRepository.findByNomeTela(dto.getNomeTela())
                .ifPresent(acesso -> {
                    throw new EntidadeJaCadastradaException("Acesso já cadastrado");
                });

        Acesso acesso = AcessoMapper.toModel(dto);
        return acessoRepository.save(acesso);
    }

    public List<Acesso> listar() {
        return acessoRepository.findAll();
    }

    public Acesso buscarPorId(Integer id) {
        return acessoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O acesso com o id não foi encontrado"));
    }

    public Acesso atualizar(Integer id, AcessoRequestDto dto) {
        if (!acessoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("O acesso com o id não foi encontrado");
        }

        Acesso acesso = AcessoMapper.toModel(dto);
        acesso.setId(id);

        return acessoRepository.save(acesso);
    }

    public void deletar(Integer id) {
        if (!acessoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("O acesso com o id não foi encontrado");
        }

        acessoRepository.deleteById(id);
    }
}
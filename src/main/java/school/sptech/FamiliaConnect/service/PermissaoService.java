package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.Permissao.PermissaoRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.PermissaoMapper;
import school.sptech.FamiliaConnect.model.Permissao;
import school.sptech.FamiliaConnect.repository.PermissaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PermissaoService {

    private final PermissaoRepository permissaoRepository;

    public PermissaoService(PermissaoRepository permissaoRepository) {
        this.permissaoRepository = permissaoRepository;
    }

    public Permissao cadastrar(PermissaoRequestDto dto) {
        Permissao permissao = PermissaoMapper.toModel(dto);
        return permissaoRepository.save(permissao);
    }

    public List<Permissao> listar() {
        return permissaoRepository.findAll();
    }

    public Permissao buscarPorId(Integer id) {
        return permissaoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("A permissão com o id não foi encontrada"));
    }

    public Permissao atualizar(Integer id, PermissaoRequestDto dto) {
        if (!permissaoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("A permissão com o id não foi encontrada");
        }

        Permissao permissao = PermissaoMapper.toModel(dto);
        permissao.setId(id);

        return permissaoRepository.save(permissao);
    }

    public void deletar(Integer id) {
        if (!permissaoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("A permissão com o id não foi encontrada");
        }

        permissaoRepository.deleteById(id);
    }

}

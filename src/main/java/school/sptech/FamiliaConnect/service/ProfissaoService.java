package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.profissao.ProfissaoRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.ProfissaoMapper;
import school.sptech.FamiliaConnect.model.Profissao;
import school.sptech.FamiliaConnect.repository.ProfissaoRepository;

import java.util.List;

@Service
public class ProfissaoService {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final ProfissaoRepository profissaoRepository;

    // Construtores ----------------------------------------------------------------------------------------------------

    public ProfissaoService(ProfissaoRepository profissaoRepository) {
        this.profissaoRepository = profissaoRepository;
    }

    // Funções ---------------------------------------------------------------------------------------------------------

    public Profissao cadastrarProfissao(Profissao profissao){

        if (profissaoRepository.existsByNome(profissao.getNome())){
            throw new EntidadeJaCadastradaException("Profissão já cadastrada");
        }

        return profissaoRepository.save(profissao);
    }

    public Profissao listarOuCadastrarPorNome(Profissao profissao){
        if (!profissaoRepository.existsByNome(profissao.getNome())) {
            return cadastrarProfissao(profissao);
        }

        return profissaoRepository.findByNome(profissao.getNome());
    }

    public List<Profissao> listarProfissoes(){

        return profissaoRepository.findAll();
    }
}

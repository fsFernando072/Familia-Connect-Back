package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.profissao.ProfissaoRequestDto;
import school.sptech.FamiliaConnect.dto.profissao.ProfissaoResponseDto;
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

    public Profissao salvar(Profissao profissao){

        if(!profissaoRepository.existsByName(profissao.getNome())){
            throw new EntidadeJaCadastradaException("Profissão já cadastrada");
        }

        Profissao profissaoSalva = profissaoRepository.save(profissao);

        return profissaoSalva;

    }

    public List<Profissao> listarProfissoes(){

        List<Profissao> profissoes = profissaoRepository.findAll();

        if(profissoes.isEmpty()){
            // mudar essa exception
            throw new EntidadeNaoEncontradaException("");
        }

        return profissoes;

    }
}

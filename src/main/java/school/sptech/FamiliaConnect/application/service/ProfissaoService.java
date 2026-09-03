package school.sptech.FamiliaConnect.application.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.application.ports.in.ProfissaoUseCase;
import school.sptech.FamiliaConnect.domain.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.domain.entity.Profissao;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.ProfissaoRepository;

import java.util.List;

@Service
public class ProfissaoService implements ProfissaoUseCase {

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

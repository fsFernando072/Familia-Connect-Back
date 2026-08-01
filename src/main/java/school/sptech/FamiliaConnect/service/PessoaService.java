package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.pessoa.PessoaRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.PessoaMapper;
import school.sptech.FamiliaConnect.model.Familia;
import school.sptech.FamiliaConnect.model.Pessoa;
import school.sptech.FamiliaConnect.model.Profissao;
import school.sptech.FamiliaConnect.repository.FamiliaRepository;
import school.sptech.FamiliaConnect.repository.PessoaRepository;
import school.sptech.FamiliaConnect.repository.ProfissaoRepository;

import java.util.Optional;

@Service
public class PessoaService {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final PessoaRepository pessoaRepository;
    private final FamiliaRepository familiaRepository;
    private final ProfissaoService profissaoService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public PessoaService(PessoaRepository pessoaRepository, FamiliaRepository familiaRepository, ProfissaoService profissaoService) {
        this.pessoaRepository = pessoaRepository;
        this.familiaRepository = familiaRepository;
        this.profissaoService = profissaoService;
    }


    // Funções ---------------------------------------------------------------------------------------------------------

    public Pessoa salvar(Pessoa pessoa){

        if (pessoaRepository.existsByCpf(pessoa.getCpf())){
           throw new EntidadeJaCadastradaException("Pessoa já cadastrada");
        }

        Familia familia = familiaRepository.findById(pessoa.getFamilia().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("A família não foi encontrada"));

        pessoa.setFamilia(familia);

        if (pessoa.getProfissao() != null &&
            pessoa.getProfissao().getNome() != null &&
            !pessoa.getProfissao().getNome().isBlank()
        ){
            Profissao profissao = profissaoService.listarOuCadastrarPorNome(pessoa.getProfissao());
            pessoa.setProfissao(profissao);
        } else {
            pessoa.setProfissao(null);
        }

        return pessoaRepository.save(pessoa);
    }

}





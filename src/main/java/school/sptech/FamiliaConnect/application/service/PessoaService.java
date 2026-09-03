package school.sptech.FamiliaConnect.application.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.application.ports.in.PessoaUseCase;
import school.sptech.FamiliaConnect.domain.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.domain.entity.Familia;
import school.sptech.FamiliaConnect.domain.entity.GrauParentesco;
import school.sptech.FamiliaConnect.domain.entity.Pessoa;
import school.sptech.FamiliaConnect.domain.entity.Profissao;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.FamiliaRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.PessoaRepository;

@Service
public class PessoaService implements PessoaUseCase {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final PessoaRepository pessoaRepository;
    private final FamiliaRepository familiaRepository;
    private final ProfissaoService profissaoService;
    private final GrauParentescoService grauParentescoService;

    // Construtores ----------------------------------------------------------------------------------------------------

    public PessoaService(PessoaRepository pessoaRepository, FamiliaRepository familiaRepository, ProfissaoService profissaoService, GrauParentescoService grauParentescoService) {
        this.pessoaRepository = pessoaRepository;
        this.familiaRepository = familiaRepository;
        this.profissaoService = profissaoService;
        this.grauParentescoService = grauParentescoService;
    }


    // Funções ---------------------------------------------------------------------------------------------------------

    public Pessoa salvar(Pessoa pessoa){

        if (pessoa.getCpf() != null && !pessoa.getCpf().isBlank() && pessoaRepository.existsByCpf(pessoa.getCpf())){
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

        GrauParentesco grauParentesco = grauParentescoService.listarPorGrau(pessoa.getGrauParentesco());
        pessoa.setGrauParentesco(grauParentesco);

        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizar(Integer id, Pessoa pessoaAtualizada) {

        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("A pessoa com o id não foi encontrada"));

        if (pessoaAtualizada.getCpf() != null &&
                !pessoaAtualizada.getCpf().isBlank() &&
                !pessoaAtualizada.getCpf().equals(pessoaExistente.getCpf()) &&
                pessoaRepository.existsByCpf(pessoaAtualizada.getCpf())
        ) {
            throw new EntidadeJaCadastradaException("Pessoa já cadastrada");
        }

        pessoaExistente.setNome(pessoaAtualizada.getNome());
        pessoaExistente.setRg(pessoaAtualizada.getRg());
        pessoaExistente.setCpf(pessoaAtualizada.getCpf());
        pessoaExistente.setDtNascimento(pessoaAtualizada.getDtNascimento());
        pessoaExistente.setTelefone(pessoaAtualizada.getTelefone());

        if (pessoaAtualizada.getProfissao() != null &&
                pessoaAtualizada.getProfissao().getNome() != null &&
                !pessoaAtualizada.getProfissao().getNome().isBlank()
        ){
            Profissao profissao = profissaoService.listarOuCadastrarPorNome(pessoaAtualizada.getProfissao());
            pessoaExistente.setProfissao(profissao);
        } else {
            pessoaExistente.setProfissao(null);
        }

        GrauParentesco grauParentesco = grauParentescoService.listarPorGrau(pessoaAtualizada.getGrauParentesco());
        pessoaExistente.setGrauParentesco(grauParentesco);

        pessoaExistente.setSexo(pessoaAtualizada.getSexo());

        return pessoaRepository.save(pessoaExistente);
    }

}





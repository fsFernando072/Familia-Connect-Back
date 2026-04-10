package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.model.Deficiencia;
import school.sptech.FamiliaConnect.model.Familia;
import school.sptech.FamiliaConnect.model.Pessoa;
import school.sptech.FamiliaConnect.model.Profissao;
import school.sptech.FamiliaConnect.repository.DeficienciaRepository;
import school.sptech.FamiliaConnect.repository.FamiliaRepository;
import school.sptech.FamiliaConnect.repository.PessoaRepository;
import school.sptech.FamiliaConnect.repository.ProfissaoRepository;

import java.util.Optional;

@Service
public class PessoaService {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final PessoaRepository pessoaRepository;
    private final DeficienciaRepository deficienciaRepository;
    private final FamiliaRepository familiaRepository;
    private final ProfissaoRepository profissaoRepository;

    // Construtores ----------------------------------------------------------------------------------------------------

    public PessoaService(PessoaRepository pessoaRepository, DeficienciaRepository deficienciaRepository, FamiliaRepository familiaRepository, ProfissaoRepository profissaoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.deficienciaRepository = deficienciaRepository;
        this.familiaRepository = familiaRepository;
        this.profissaoRepository = profissaoRepository;
    }


    // Funções ---------------------------------------------------------------------------------------------------------

    public Pessoa salvar(Pessoa pessoa, Integer idDeficiencia, Integer idFamilia, Integer idProfissao){

        if(pessoaRepository.existsByCpf(pessoa.getCpf())){
           throw new EntidadeJaCadastradaException("Entidade já cadastrada");
        }

        Optional<Familia> familia = familiaRepository.findById(idFamilia);
        pessoa.setFamilia(familia.get());

        if(idDeficiencia != null){
            Optional<Deficiencia> deficiencia = deficienciaRepository.findById(idDeficiencia);
            pessoa.setDeficiencia(deficiencia.get());
        }

        if(idProfissao != null){
            Optional<Profissao> profissao = profissaoRepository.findById(idProfissao);
            pessoa.setProfissao(profissao.get());
        }

        Pessoa pessoaCadastrada = pessoaRepository.save(pessoa);

        return pessoaCadastrada;

    }

}





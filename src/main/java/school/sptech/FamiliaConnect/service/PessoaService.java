package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.pessoa.PessoaRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.PessoaMapper;
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

    public Pessoa salvar(PessoaRequestDto dto){

        if (pessoaRepository.existsByCpf(dto.getCpf())){
           throw new EntidadeJaCadastradaException("Pessoa já cadastrada");
        }

        Familia familia = familiaRepository.findById(dto.getIdFamilia())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("A família não foi encontrada"));



        Pessoa pessoa = PessoaMapper.toModel(dto);
        pessoa.setFamilia(familia);

        if(dto.getIdDeficiencia() != null){
            Optional<Deficiencia> deficiencia = deficienciaRepository.findById(dto.getIdDeficiencia());
            pessoa.setDeficiencia(deficiencia.get());
        }

        if(dto.getIdProfissao() != null){
            Optional<Profissao> profissao = profissaoRepository.findById(dto.getIdProfissao());
            pessoa.setProfissao(profissao.get());
        }

        return pessoaRepository.save(pessoa);
    }

}





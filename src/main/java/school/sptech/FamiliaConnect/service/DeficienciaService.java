package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.deficiencia.DeficienciaRequestDto;
import school.sptech.FamiliaConnect.dto.deficiencia.DeficienciaResponseDto;
import school.sptech.FamiliaConnect.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.DeficienciaMapper;
import school.sptech.FamiliaConnect.model.Deficiencia;
import school.sptech.FamiliaConnect.repository.DeficienciaRepository;

import java.util.List;

@Service
public class DeficienciaService {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final DeficienciaRepository deficienciaRepository;

    // Construtores ----------------------------------------------------------------------------------------------------

    public DeficienciaService(DeficienciaRepository deficienciaRepository) {
        this.deficienciaRepository = deficienciaRepository;
    }

    // Funções ---------------------------------------------------------------------------------------------------------

    public Deficiencia salvarDeficiencia(Deficiencia deficiencia){

        if(!deficienciaRepository.existsByNome(deficiencia.getNome())){
            throw new EntidadeJaCadastradaException("Deficiencia já cadastrada");
        }

        Deficiencia deficienciaCadastrada = deficienciaRepository.save(deficiencia);

        return deficienciaCadastrada;

    }

    public List<Deficiencia> listarDeficiencias(){

        List<Deficiencia> deficiencias = deficienciaRepository.findAll();

        // Arrumar a exception
        if(deficiencias.isEmpty()){
            throw new EntidadeNaoEncontradaException("Entidade não encontrada");
        }

        return deficiencias;

    }

}

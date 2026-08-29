package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.model.GrauParentesco;
import school.sptech.FamiliaConnect.model.Profissao;
import school.sptech.FamiliaConnect.repository.GrauParentescoRepository;


import java.util.List;

@Service
public class GrauParentescoService {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final GrauParentescoRepository grauParentescoRepository;

    // Construtores ----------------------------------------------------------------------------------------------------

    public GrauParentescoService(GrauParentescoRepository grauParentescoRepository) {
        this.grauParentescoRepository = grauParentescoRepository;
    }

    // Funções ---------------------------------------------------------------------------------------------------------


    public GrauParentesco listarPorGrau(GrauParentesco grauParentesco){
        if (!grauParentescoRepository.existsByGrau(grauParentesco.getGrau())) {
            throw new EntidadeNaoEncontradaException("O grau de parentesco não foi encontrado");
        }

        return grauParentescoRepository.findByGrau(grauParentesco.getGrau());
    }

    public List<GrauParentesco> listarGrauParentesco(){

        return grauParentescoRepository.findAll();
    }
}

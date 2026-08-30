package school.sptech.FamiliaConnect.application.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.application.ports.in.GrauParentescoUseCase;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.domain.entity.GrauParentesco;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.GrauParentescoRepository;


import java.util.List;

@Service
public class GrauParentescoService implements GrauParentescoUseCase {

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

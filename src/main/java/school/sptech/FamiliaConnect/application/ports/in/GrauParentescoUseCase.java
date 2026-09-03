package school.sptech.FamiliaConnect.application.ports.in;

import school.sptech.FamiliaConnect.domain.entity.GrauParentesco;

import java.util.List;

public interface GrauParentescoUseCase {

    GrauParentesco listarPorGrau(GrauParentesco grauParentesco);
    List<GrauParentesco> listarGrauParentesco();


}

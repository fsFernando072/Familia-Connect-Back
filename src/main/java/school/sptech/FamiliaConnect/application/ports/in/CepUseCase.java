package school.sptech.FamiliaConnect.application.ports.in;

import school.sptech.FamiliaConnect.domain.entity.EnderecoCep;

public interface CepUseCase {

    EnderecoCep buscarEnderecoPorCep(String cep);

}

package school.sptech.FamiliaConnect.application.ports.in;

import school.sptech.FamiliaConnect.domain.entity.Endereco;

public interface EnderecoUseCase {

    Endereco salvar(Endereco endereco);
    Endereco atualizar(Integer id, Endereco enderecoAtualizado);



}

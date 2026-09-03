package school.sptech.FamiliaConnect.application.ports.in;

import school.sptech.FamiliaConnect.domain.entity.Pessoa;

public interface PessoaUseCase {

    Pessoa salvar(Pessoa pessoa);
    Pessoa atualizar(Integer id, Pessoa pessoaAtualizada);


}

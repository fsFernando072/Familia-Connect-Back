package school.sptech.FamiliaConnect.application.ports.in;

import school.sptech.FamiliaConnect.domain.entity.Profissao;

import java.util.List;

public interface ProfissaoUseCase {

    Profissao cadastrarProfissao(Profissao profissao);
    Profissao listarOuCadastrarPorNome(Profissao profissao);
    List<Profissao> listarProfissoes();

}

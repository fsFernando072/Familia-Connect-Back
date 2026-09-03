package school.sptech.FamiliaConnect.application.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.application.ports.in.EntregaUseCase;
import school.sptech.FamiliaConnect.domain.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.domain.exception.EstoqueInsuficienteException;
import school.sptech.FamiliaConnect.domain.entity.Entrega;
import school.sptech.FamiliaConnect.domain.entity.Funcionario;
import school.sptech.FamiliaConnect.domain.entity.HistoricoEstoque;
import school.sptech.FamiliaConnect.domain.entity.Pessoa;
import school.sptech.FamiliaConnect.domain.entity.Produto;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.EntregaRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.FuncionarioRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.HistoricoEstoqueRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.PessoaRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.ProdutoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class EntregaService implements EntregaUseCase {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final EntregaRepository entregaRepository;
    private final PessoaRepository pessoaRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ProdutoRepository produtoRepository;
    private final HistoricoEstoqueRepository historicoEstoqueRepository;

    // Construtores ----------------------------------------------------------------------------------------------------

    public EntregaService(EntregaRepository entregaRepository, PessoaRepository pessoaRepository, FuncionarioRepository funcionarioRepository, ProdutoRepository produtoRepository, HistoricoEstoqueRepository historicoEstoqueRepository) {
        this.entregaRepository = entregaRepository;
        this.pessoaRepository = pessoaRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.produtoRepository = produtoRepository;
        this.historicoEstoqueRepository = historicoEstoqueRepository;
    }

    // Funções ---------------------------------------------------------------------------------------------------------

    public List<Entrega> listar(){

        return entregaRepository.findAll();

    }

    public Entrega listarPorId(Integer id){

        return entregaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada pelo id"));
    }

    public Entrega salvar(Entrega entrega){

        Pessoa pessoa = pessoaRepository.findById(entrega.getPessoa().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa não encontrada pelo id"));

        Funcionario funcionario = funcionarioRepository.findById(entrega.getFuncionario().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado pelo id"));

        Produto produto = produtoRepository.findById(entrega.getProduto().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado pelo id"));


        entrega.setPessoa(pessoa);
        entrega.setFuncionario(funcionario);
        entrega.setProduto(produto);

        validarRegrasDeEntrega(entrega, null);

        return entregaRepository.save(entrega);

    }

    public Entrega atualizar(Integer id, Entrega entrega){

        if(!entregaRepository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Entrega não encontrada pelo id");
        }

        Pessoa pessoa = pessoaRepository.findById(entrega.getPessoa().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa não encontrada pelo id"));

        Funcionario funcionario = funcionarioRepository.findById(entrega.getFuncionario().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado pelo id"));

        Produto produto = produtoRepository.findById(entrega.getProduto().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado pelo id"));

        entrega.setId(id);
        entrega.setPessoa(pessoa);
        entrega.setFuncionario(funcionario);
        entrega.setProduto(produto);

        validarRegrasDeEntrega(entrega, id);

        return entregaRepository.save(entrega);

    }

    public void deletar(Integer id){

        if(!entregaRepository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Entrega não encontrada pelo id");
        }

        entregaRepository.deleteById(id);

    }

    // Regras de negócio -------------------------------------------------------------------------------------------

    private void validarRegrasDeEntrega(Entrega entrega, Integer idEntregaAtual) {

        LocalDate hoje = LocalDate.now();
        LocalDate inicioMes = hoje.withDayOfMonth(1);
        LocalDate fimMes = hoje.withDayOfMonth(hoje.lengthOfMonth());

        // Só pode cadastrar entrega se houver estoque cadastrado para o produto no mês vigente
        List<HistoricoEstoque> estoquesDoMes = historicoEstoqueRepository
                .findByProdutoIdAndDataEstoqueBetween(entrega.getProduto().getId(), inicioMes, fimMes);

        if (estoquesDoMes.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    "Não há estoque cadastrado para este produto no mês atual, não é possível registrar a entrega"
            );
        }

        double quantidadeCadastradaNoMes = estoquesDoMes.stream()
                .mapToDouble(HistoricoEstoque::getQuantidade)
                .sum();

        // A quantidade já entregue no mês (contando esta entrega) não pode ultrapassar a quantidade cadastrada
        long entregasDoProdutoNoMes = entregaRepository
                .findByProdutoIdAndDataEntregaBetween(entrega.getProduto().getId(), inicioMes, fimMes)
                .stream()
                .filter(e -> idEntregaAtual == null || !e.getId().equals(idEntregaAtual))
                .count();

        if (entregasDoProdutoNoMes + 1 > quantidadeCadastradaNoMes) {
            throw new EstoqueInsuficienteException(
                    "A quantidade de entregas deste produto no mês atingiria o limite cadastrado no estoque"
            );
        }

        boolean familiaJaRecebeuEntregaNoMes = entregaRepository
                .findByPessoa_FamiliaIdAndDataEntregaBetween(entrega.getPessoa().getFamilia().getId(), inicioMes, fimMes)
                .stream()
                .anyMatch(e -> idEntregaAtual == null || !e.getId().equals(idEntregaAtual));

        if (familiaJaRecebeuEntregaNoMes) {
            throw new EntidadeJaCadastradaException("Esta família já recebeu uma entrega neste mês");
        }

    }

}

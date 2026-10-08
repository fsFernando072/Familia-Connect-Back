package school.sptech.FamiliaConnect.application.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import school.sptech.FamiliaConnect.infraestructure.web.dto.entrega.EntregaHistoricoLinhaProjection;
import school.sptech.FamiliaConnect.infraestructure.web.dto.entrega.EntregaHistoricoResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.entrega.FamiliaPendenteEntregaResponseDto;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    // Entrega completa de uma família ----------------------------------------------------------------------------------

    // Registra todos os produtos de uma família de uma vez. Tudo ou nada: se qualquer regra falhar,
    // nada é salvo. Cada unidade vira um registro de entrega (a tabela não tem coluna de quantidade).
    @Transactional
    public List<Entrega> salvarLote(Integer idPessoa, Integer idFuncionario, LocalDate dataEntrega, Map<Integer, Integer> quantidadesPorProduto){

        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa não encontrada pelo id"));

        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado pelo id"));

        LocalDate hoje = LocalDate.now();
        LocalDate inicioMes = hoje.withDayOfMonth(1);
        LocalDate fimMes = hoje.withDayOfMonth(hoje.lengthOfMonth());

        boolean familiaJaRecebeuEntregaNoMes = !entregaRepository
                .findByPessoa_FamiliaIdAndDataEntregaBetween(pessoa.getFamilia().getId(), inicioMes, fimMes)
                .isEmpty();

        if (familiaJaRecebeuEntregaNoMes) {
            throw new EntidadeJaCadastradaException("Esta família já recebeu uma entrega neste mês");
        }

        List<Entrega> novasEntregas = new ArrayList<>();

        for (Map.Entry<Integer, Integer> item : quantidadesPorProduto.entrySet()) {

            Produto produto = produtoRepository.findById(item.getKey())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado pelo id"));

            validarEstoqueDoMes(produto, item.getValue(), inicioMes, fimMes);

            for (int i = 0; i < item.getValue(); i++) {
                Entrega entrega = new Entrega();
                entrega.setPessoa(pessoa);
                entrega.setFuncionario(funcionario);
                entrega.setProduto(produto);
                entrega.setDataEntrega(dataEntrega);
                novasEntregas.add(entrega);
            }
        }

        return entregaRepository.saveAll(novasEntregas);
    }

    // Listas para as telas ---------------------------------------------------------------------------------------------

    // Famílias que não receberam entrega no mês informado (padrão: mês atual).
    // No mês seguinte, ninguém tem entrega e todas as famílias voltam a aparecer.
    public Page<FamiliaPendenteEntregaResponseDto> listarFamiliasPendentes(String nomeResponsavel, YearMonth mes, Pageable pageable){

        YearMonth mesConsultado = mes != null ? mes : YearMonth.now();
        String termoPesquisa = nomeResponsavel != null ? nomeResponsavel : "";

        return entregaRepository.findFamiliasPendentesDeEntrega(
                termoPesquisa,
                mesConsultado.atDay(1),
                mesConsultado.atEndOfMonth(),
                pageable
        );
    }

    // Histórico com uma linha por família e mês, listando todos os produtos recebidos.
    // Sem mês informado, traz todos os meses. A ordenação é pela data da entrega (padrão: mais recentes primeiro).
    public Page<EntregaHistoricoResponseDto> listarHistorico(String nomeResponsavel, YearMonth mes, Pageable pageable){

        String termoPesquisa = nomeResponsavel != null ? nomeResponsavel : "";
        LocalDate inicio = mes != null ? mes.atDay(1) : LocalDate.of(1900, 1, 1);
        LocalDate fim = mes != null ? mes.atEndOfMonth() : LocalDate.of(9999, 12, 31);

        List<EntregaHistoricoLinhaProjection> linhas = entregaRepository.findHistoricoLinhas(termoPesquisa, inicio, fim);

        Map<String, List<EntregaHistoricoLinhaProjection>> linhasPorFamiliaEMes = new LinkedHashMap<>();
        for (EntregaHistoricoLinhaProjection linha : linhas) {
            String chave = linha.getIdFamilia() + "|" + YearMonth.from(linha.getDataDaEntrega());
            linhasPorFamiliaEMes.computeIfAbsent(chave, k -> new ArrayList<>()).add(linha);
        }

        List<EntregaHistoricoResponseDto> historico = new ArrayList<>();
        for (List<EntregaHistoricoLinhaProjection> grupo : linhasPorFamiliaEMes.values()) {
            historico.add(montarHistorico(grupo));
        }

        Sort.Order ordem = pageable.getSort().getOrderFor("dataDaEntrega");
        boolean maisRecentesPrimeiro = ordem == null || ordem.isDescending();

        Comparator<EntregaHistoricoResponseDto> comparador = Comparator
                .comparing(EntregaHistoricoResponseDto::getDataDaEntrega)
                .thenComparing(EntregaHistoricoResponseDto::getIdFamilia);

        historico.sort(maisRecentesPrimeiro ? comparador.reversed() : comparador);

        int inicioPagina = (int) Math.min(pageable.getOffset(), historico.size());
        int fimPagina = Math.min(inicioPagina + pageable.getPageSize(), historico.size());

        return new PageImpl<>(historico.subList(inicioPagina, fimPagina), pageable, historico.size());
    }

    // Exclui a entrega inteira de uma família em um mês (todos os produtos). Assim, se for o mês atual,
    // a família volta a ficar pendente e aparece de novo na tela de entrega.
    @Transactional
    public void deletarPorFamiliaNoMes(Integer idFamilia, YearMonth mes){

        List<Entrega> entregas = entregaRepository
                .findByPessoa_FamiliaIdAndDataEntregaBetween(idFamilia, mes.atDay(1), mes.atEndOfMonth());

        if (entregas.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhuma entrega encontrada para esta família no mês informado");
        }

        entregaRepository.deleteAll(entregas);
    }

    // Junta as linhas de uma família no mês em uma só: produtos separados por vírgula, com ×N quando repetido.
    private EntregaHistoricoResponseDto montarHistorico(List<EntregaHistoricoLinhaProjection> grupo) {

        EntregaHistoricoLinhaProjection primeira = grupo.get(0);
        LocalDate primeiraData = primeira.getDataDaEntrega();

        Map<String, Integer> quantidadePorProduto = new LinkedHashMap<>();
        for (EntregaHistoricoLinhaProjection linha : grupo) {
            quantidadePorProduto.merge(linha.getNomeProduto(), 1, Integer::sum);
            if (linha.getDataDaEntrega().isBefore(primeiraData)) {
                primeiraData = linha.getDataDaEntrega();
            }
        }

        List<String> produtos = new ArrayList<>();
        for (Map.Entry<String, Integer> produto : quantidadePorProduto.entrySet()) {
            produtos.add(produto.getValue() > 1 ? produto.getKey() + " ×" + produto.getValue() : produto.getKey());
        }

        return new EntregaHistoricoResponseDto(
                primeira.getIdFamilia(),
                YearMonth.from(primeiraData).toString(),
                primeiraData,
                String.join(", ", produtos),
                primeira.getNomeFamilia(),
                primeira.getNomeResponsavel(),
                primeira.getTelefoneResponsavel(),
                primeira.getFotoFamilia()
        );
    }

    // Mesma regra de estoque da entrega unitária, mas para uma quantidade de unidades do produto.
    private void validarEstoqueDoMes(Produto produto, int quantidade, LocalDate inicioMes, LocalDate fimMes) {

        List<HistoricoEstoque> estoquesDoMes = historicoEstoqueRepository
                .findByProdutoIdAndDataEstoqueBetween(produto.getId(), inicioMes, fimMes);

        if (estoquesDoMes.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    "Não há estoque cadastrado para o produto \"" + produto.getNome() + "\" no mês atual, não é possível registrar a entrega"
            );
        }

        double quantidadeCadastradaNoMes = estoquesDoMes.stream()
                .mapToDouble(HistoricoEstoque::getQuantidade)
                .sum();

        long entregasDoProdutoNoMes = entregaRepository
                .findByProdutoIdAndDataEntregaBetween(produto.getId(), inicioMes, fimMes)
                .size();

        if (entregasDoProdutoNoMes + quantidade > quantidadeCadastradaNoMes) {
            long restante = Math.max(0, (long) (quantidadeCadastradaNoMes - entregasDoProdutoNoMes));
            throw new EstoqueInsuficienteException(
                    "Estoque insuficiente do produto \"" + produto.getNome() + "\" neste mês (restam " + restante + ")"
            );
        }
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

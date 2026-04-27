package school.sptech.FamiliaConnect.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.dto.entrega.EntregaRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.EntregaMapper;
import school.sptech.FamiliaConnect.model.Entrega;
import school.sptech.FamiliaConnect.model.Funcionario;
import school.sptech.FamiliaConnect.model.Pessoa;
import school.sptech.FamiliaConnect.model.Produto;
import school.sptech.FamiliaConnect.repository.EntregaRepository;
import school.sptech.FamiliaConnect.repository.FuncionarioRepository;
import school.sptech.FamiliaConnect.repository.PessoaRepository;
import school.sptech.FamiliaConnect.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private final EntregaRepository entregaRepository;
    private final PessoaRepository pessoaRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ProdutoRepository produtoRepository;

    // Construtores ----------------------------------------------------------------------------------------------------

    public EntregaService(EntregaRepository entregaRepository, PessoaRepository pessoaRepository, FuncionarioRepository funcionarioRepository, ProdutoRepository produtoRepository) {
        this.entregaRepository = entregaRepository;
        this.pessoaRepository = pessoaRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.produtoRepository = produtoRepository;
    }

    // Funções ---------------------------------------------------------------------------------------------------------

    public List<Entrega> listar(){

        return entregaRepository.findAll();

    }

    public Entrega listarPorId(Integer id){

        Optional<Entrega> entrega = entregaRepository.findById(id);

        if(entrega.isEmpty()){
            throw new EntidadeNaoEncontradaException("Entrega não encontrada pelo id");
        }

        return entrega.get();

    }

    public Entrega salvar(EntregaRequestDto requestDto){

        Pessoa pessoa = pessoaRepository.findById(requestDto.getIdPessoa())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa não encontrada pelo id"));

        Funcionario funcionario = funcionarioRepository.findById(requestDto.getIdFuncionario())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado pelo id"));

        Produto produto = produtoRepository.findById(requestDto.getIdProduto())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado pelo id"));

        Entrega entrega = EntregaMapper.toModel(requestDto);
        entrega.setPessoa(pessoa);
        entrega.setFuncionario(funcionario);
        entrega.setProduto(produto);

        return entregaRepository.save(entrega);

    }

}

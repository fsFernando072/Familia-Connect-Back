package school.sptech.FamiliaConnect.infraestructure.web.mapper;

import school.sptech.FamiliaConnect.infraestructure.web.dto.entrega.EntregaRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.entrega.EntregaResponseDto;
import school.sptech.FamiliaConnect.domain.entity.Entrega;
import school.sptech.FamiliaConnect.domain.entity.Funcionario;
import school.sptech.FamiliaConnect.domain.entity.Pessoa;
import school.sptech.FamiliaConnect.domain.entity.Produto;

import java.util.List;

public class EntregaMapper {

    public static Entrega toModel(EntregaRequestDto requestDto) {

        Entrega entrega = new Entrega();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(requestDto.getIdPessoa());

        Funcionario funcionario = new Funcionario();
        funcionario.setId(requestDto.getIdFuncionario());

        Produto produto = new Produto();
        produto.setId(requestDto.getIdProduto());

        entrega.setPessoa(pessoa);
        entrega.setFuncionario(funcionario);
        entrega.setProduto(produto);

        return entrega;

    }

    public static EntregaResponseDto toResponse(Entrega entrega) {

        EntregaResponseDto.EntregaFuncionario entregaFuncionario = new EntregaResponseDto.EntregaFuncionario(
                entrega.getFuncionario().getId(),
                entrega.getFuncionario().getNome()
        );

        EntregaResponseDto.EntregaPessoa entregaPessoa = new EntregaResponseDto.EntregaPessoa(
          entrega.getPessoa().getId(),
                entrega.getPessoa().getNome()
        );

        EntregaResponseDto.EntregaProduto entregaProduto = new EntregaResponseDto.EntregaProduto(
                entrega.getProduto().getId(),
                entrega.getProduto().getNome()
        );

        EntregaResponseDto entregaResponseDto = new EntregaResponseDto(
                entrega.getId(),
                entrega.getDataEntrega(),
                entregaFuncionario,
                entregaPessoa,
                entregaProduto
        );

        return entregaResponseDto;

    }

    public static List<EntregaResponseDto> toResponse(List<Entrega> entregas) {
        return entregas.stream()
                .map(EntregaMapper::toResponse)
                .toList();
    }

}

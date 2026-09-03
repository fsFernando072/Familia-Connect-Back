package school.sptech.FamiliaConnect.infraestructure.web.mapper;

import school.sptech.FamiliaConnect.infraestructure.web.dto.historicoEstoque.HistoricoEstoqueRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.historicoEstoque.HistoricoEstoqueResponseDto;
import school.sptech.FamiliaConnect.domain.entity.Categoria;
import school.sptech.FamiliaConnect.domain.entity.HistoricoEstoque;
import school.sptech.FamiliaConnect.domain.entity.Produto;

import java.util.List;

public class HistoricoEstoqueMapper {

    public static HistoricoEstoque toModel(HistoricoEstoqueRequestDto dto) {

        HistoricoEstoque historicoEstoque = new HistoricoEstoque();
        historicoEstoque.setQuantidade(dto.getQuantidade());

        Produto produto = new Produto();
        produto.setId(dto.getIdProduto());

        historicoEstoque.setProduto(produto);

        return historicoEstoque;

    }

    public static HistoricoEstoqueResponseDto toResponse(HistoricoEstoque historicoEstoque) {

        HistoricoEstoqueResponseDto responseDto = new HistoricoEstoqueResponseDto();
        responseDto.setId(historicoEstoque.getId());
        responseDto.setQuantidade(historicoEstoque.getQuantidade());
        responseDto.setDataEstoque(historicoEstoque.getDataEstoque());

        Produto produto = historicoEstoque.getProduto();

        HistoricoEstoqueResponseDto.ProdutoHistoricoResponseDto produtoDto = responseDto.new ProdutoHistoricoResponseDto();
        produtoDto.setId(produto.getId());
        produtoDto.setNome(produto.getNome());
        produtoDto.setDescricao(produto.getDescricao());

        Categoria categoria = produto.getCategoria();

        if (categoria != null) {
            HistoricoEstoqueResponseDto.ProdutoHistoricoResponseDto.CategoriaProdutoResponseDto categoriaDto =
                    produtoDto.new CategoriaProdutoResponseDto();
            categoriaDto.setId(categoria.getId());
            categoriaDto.setNome(categoria.getNome());

            produtoDto.setCategoria(categoriaDto);
        }

        responseDto.setProduto(produtoDto);

        return responseDto;

    }

    public static List<HistoricoEstoqueResponseDto> toResponse(List<HistoricoEstoque> historicos) {
        return historicos.stream()
                .map(HistoricoEstoqueMapper::toResponse)
                .toList();
    }

}

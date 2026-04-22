package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.produto.ProdutoRequestDto;
import school.sptech.FamiliaConnect.dto.produto.ProdutoResponseDto;
import school.sptech.FamiliaConnect.model.Produto;

import java.util.List;

public class ProdutoMapper {

    public static Produto toModel(ProdutoRequestDto dto){

        Produto produto = new Produto();
        produto.setDescricao(dto.getDescricao());
        produto.setNome(dto.getNome());
        produto.setQuantidade(dto.getQuantidade());

        return produto;

    }

    public static ProdutoResponseDto toResponse(Produto produto){

        ProdutoResponseDto.ProdutoCategoria produtoCategoria = new ProdutoResponseDto.ProdutoCategoria(
           produto.getCategoria().getId(),
           produto.getCategoria().getNome()
        );

        ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto(
            produto.getId(),
                produto.getNome(),
                produto.getQuantidade(),
                produto.getDescricao(),
                produtoCategoria
        );

        return produtoResponseDto;

    }

    public static List<ProdutoResponseDto> toResponseList(List<Produto> produtos){

        return produtos.stream()
                .map(ProdutoMapper::toResponse)
                .toList();

    }
}

package school.sptech.FamiliaConnect.infraestructure.web.mapper;

import school.sptech.FamiliaConnect.infraestructure.web.dto.produto.ProdutoRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.produto.ProdutoResponseDto;
import school.sptech.FamiliaConnect.domain.entity.Categoria;
import school.sptech.FamiliaConnect.domain.entity.Produto;

import java.util.List;

public class ProdutoMapper {

    public static Produto toModel(ProdutoRequestDto dto){

        Produto produto = new Produto();
        produto.setDescricao(dto.getDescricao());
        produto.setNome(dto.getNome());

        Categoria categoria = new Categoria();
        categoria.setId(dto.getIdCategoria());

        produto.setCategoria(categoria);

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

package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.categoria.CategoriaRequestDto;
import school.sptech.FamiliaConnect.dto.categoria.CategoriaResponseDto;
import school.sptech.FamiliaConnect.model.Categoria;

import java.util.List;

public class CategoriaMapper {

    public static Categoria toModel(CategoriaRequestDto requestDto){

        Categoria categoria = new Categoria();
        categoria.setNome(requestDto.getNome());

        return categoria;

    }

    public static CategoriaResponseDto toResponse(Categoria categoria) {

        CategoriaResponseDto categoriaResponseDto = new CategoriaResponseDto();
        categoriaResponseDto.setId(categoria.getId());
        categoriaResponseDto.setNome(categoria.getNome());

        return categoriaResponseDto;

    }

    public static List<CategoriaResponseDto> toResponseList(List<Categoria> categorias){

        return categorias.stream()
                .map(CategoriaMapper::toResponse)
                .toList();

    }
}

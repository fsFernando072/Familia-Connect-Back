package school.sptech.FamiliaConnect.infraestructure.web.mapper;

import school.sptech.FamiliaConnect.infraestructure.web.dto.categoriaArquivo.CategoriaArquivoRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.categoriaArquivo.CategoriaArquivoResponseDto;
import school.sptech.FamiliaConnect.domain.entity.CategoriaArquivo;

import java.util.List;

public class CategoriaArquivoMapper {

    public static CategoriaArquivo toModel(CategoriaArquivoRequestDto dto){

        CategoriaArquivo categoriaArquivo = new CategoriaArquivo();
        categoriaArquivo.setNome(dto.getNome());

        return categoriaArquivo;

    }

    public static CategoriaArquivoResponseDto toResponse(CategoriaArquivo categoriaArquivo){

        return new CategoriaArquivoResponseDto(
                categoriaArquivo.getId(),
                categoriaArquivo.getNome()
        );

    }

    public static List<CategoriaArquivoResponseDto> toResponse(List<CategoriaArquivo> categorias){

        return categorias.stream()
                .map(CategoriaArquivoMapper::toResponse)
                .toList();

    }

}

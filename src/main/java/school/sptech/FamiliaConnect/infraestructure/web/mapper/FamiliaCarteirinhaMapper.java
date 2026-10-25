package school.sptech.FamiliaConnect.infraestructure.web.mapper;


import org.mapstruct.Mapper;
import school.sptech.FamiliaConnect.domain.entity.FamiliaCarteirinha;
import school.sptech.FamiliaConnect.domain.entity.carteirinha.StatusCarteirinha;
import school.sptech.FamiliaConnect.infraestructure.web.dto.carteirinha.StatusCarteirinhaResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.familia.FamiliaCarteirinhaResponseDto;

@Mapper(componentModel = "spring")
public interface FamiliaCarteirinhaMapper {

    FamiliaCarteirinhaResponseDto toResponse(FamiliaCarteirinha familiaCarteirinha);
    StatusCarteirinhaResponseDto toResponse(StatusCarteirinha statusCarteirinha);

}

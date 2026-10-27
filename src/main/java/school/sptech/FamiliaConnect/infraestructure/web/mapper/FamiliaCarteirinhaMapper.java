package school.sptech.FamiliaConnect.infraestructure.web.mapper;


import org.mapstruct.Mapper;
import school.sptech.FamiliaConnect.domain.entity.carteirinha.ProcessamentoCarteirinha;
import school.sptech.FamiliaConnect.domain.entity.carteirinha.StatusCarteirinha;
import school.sptech.FamiliaConnect.infraestructure.web.dto.carteirinha.ProcessamentoCarteirinhaResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.carteirinha.StatusCarteirinhaResponseDto;

@Mapper(componentModel = "spring")
public interface FamiliaCarteirinhaMapper {

    ProcessamentoCarteirinhaResponseDto toResponse(ProcessamentoCarteirinha familiaCarteirinha);
    StatusCarteirinhaResponseDto toResponse(StatusCarteirinha statusCarteirinha);

}

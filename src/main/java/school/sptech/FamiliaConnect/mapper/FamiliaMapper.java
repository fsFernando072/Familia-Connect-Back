package school.sptech.FamiliaConnect.mapper;

import org.springframework.data.domain.Page;
import school.sptech.FamiliaConnect.dto.familia.FamiliaRequestDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaResponseDto;
import school.sptech.FamiliaConnect.model.Endereco;
import school.sptech.FamiliaConnect.model.Estado;
import school.sptech.FamiliaConnect.model.Familia;

import java.util.List;

public class FamiliaMapper {

    public static Familia toModel(FamiliaRequestDto familiaRequestDto){

        Familia familia = new Familia();
        familia.setFotoFamilia(familiaRequestDto.getFotoFamilia());
        familia.setDataCadastro(familiaRequestDto.getDataCadastro());
        familia.setPossuiPrioridade(familiaRequestDto.getPossuiPrioridade());
        Endereco endereco = new Endereco();
        endereco.setId(familiaRequestDto.getEnderecoId());

        familia.setEndereco(endereco);

        return familia;
    }

    public static FamiliaResponseDto toResponse(Familia familia){

        Endereco enderecoEntidade = familia.getEndereco();

        FamiliaResponseDto.FamiliaEndereco familiaEndereco =
                new FamiliaResponseDto.FamiliaEndereco();

        familiaEndereco.setId(enderecoEntidade.getId());
        familiaEndereco.setCep(enderecoEntidade.getCep());
        familiaEndereco.setBairro(enderecoEntidade.getBairro());
        familiaEndereco.setLogradouro(enderecoEntidade.getLogradouro());
        familiaEndereco.setNumero(enderecoEntidade.getNumero());
        familiaEndereco.setCidade(enderecoEntidade.getCidade());
        familiaEndereco.setComplemento(enderecoEntidade.getComplemento());

        Estado estado = enderecoEntidade.getEstado();

        FamiliaResponseDto.FamiliaEndereco.EnderecoEstado enderecoEstado =
                new FamiliaResponseDto.FamiliaEndereco.EnderecoEstado();

        enderecoEstado.setId(estado.getId());
        enderecoEstado.setNome(estado.getNome());
        enderecoEstado.setSigla(estado.getSigla());

        familiaEndereco.setEnderecoEstado(enderecoEstado);

        FamiliaResponseDto dto = new FamiliaResponseDto();
        dto.setId(familia.getId());
        dto.setDataCadastro(familia.getDataCadastro());
        dto.setFotoFamilia(familia.getFotoFamilia());
        dto.setPossuiPrioridade(familia.getPossuiPrioridade());
        dto.setFamiliaEndereco(familiaEndereco);

        return dto;
    }

    public static List<FamiliaResponseDto> toResponse(List<Familia> familias){

        return familias.stream()
                .map(FamiliaMapper::toResponse)
                .toList();

    }

    public static Page<FamiliaResponseDto> toResponse(Page<Familia> familias){

        return familias.map(FamiliaMapper::toResponse);

    }

}

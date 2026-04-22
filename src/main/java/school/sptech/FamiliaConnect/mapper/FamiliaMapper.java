package school.sptech.FamiliaConnect.mapper;

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

        return familia;
    }

    public static FamiliaResponseDto toResponse(Familia familia){

        Endereco enderecoEntidade = new Endereco();
        FamiliaResponseDto.FamiliaEndereco familiaEndereco = new FamiliaResponseDto.FamiliaEndereco();
        familiaEndereco.setCep(enderecoEntidade.getCep());
        familiaEndereco.setBairro(enderecoEntidade.getBairro());
        familiaEndereco.setLogradouro(enderecoEntidade.getLogradouro());
        familiaEndereco.setNumero(enderecoEntidade.getNumero());
        familiaEndereco.setLogradouro(enderecoEntidade.getLogradouro());
        familiaEndereco.setCidade(enderecoEntidade.getCidade());

        Estado estadoEntidade = new Estado();
        FamiliaResponseDto.FamiliaEndereco.EnderecoEstado enderecoEstado = new FamiliaResponseDto.FamiliaEndereco.EnderecoEstado();
        enderecoEstado.setNome(estadoEntidade.getNome());
        enderecoEstado.setSigla(estadoEntidade.getSigla());

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

}

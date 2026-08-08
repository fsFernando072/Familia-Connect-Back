package school.sptech.FamiliaConnect.mapper;

import org.springframework.data.domain.Page;
import school.sptech.FamiliaConnect.dto.endereco.EnderecoResponseDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaDetalhesResponseDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaResponseDto;
import school.sptech.FamiliaConnect.model.Endereco;
import school.sptech.FamiliaConnect.model.Estado;
import school.sptech.FamiliaConnect.model.Familia;
import school.sptech.FamiliaConnect.model.Pessoa;

import java.util.List;

public class FamiliaMapper {

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

    public static FamiliaDetalhesResponseDto toDetalhes(Familia familia, List<Pessoa> integrantes){

        FamiliaDetalhesResponseDto dto = new FamiliaDetalhesResponseDto();
        dto.setId(familia.getId());
        dto.setDataCadastro(familia.getDataCadastro());
        dto.setFotoFamilia(familia.getFotoFamilia());
        dto.setPossuiPrioridade(familia.getPossuiPrioridade());
        dto.setEndereco(EnderecoMapper.toResponse(familia.getEndereco()));

        integrantes.stream()
                .filter(Pessoa::getResponsavel)
                .findFirst()
                .ifPresent(responsavel -> dto.setResponsavel(toResponsavel(responsavel)));

        List<FamiliaDetalhesResponseDto.Dependente> dependentes = integrantes.stream()
                .filter(pessoa -> !Boolean.TRUE.equals(pessoa.getResponsavel()))
                .map(FamiliaMapper::toDependente)
                .toList();

        dto.setDependentes(dependentes);

        return dto;
    }

    private static FamiliaDetalhesResponseDto.Responsavel toResponsavel(Pessoa pessoa){

        FamiliaDetalhesResponseDto.Responsavel responsavel = new FamiliaDetalhesResponseDto.Responsavel();
        responsavel.setId(pessoa.getId());
        responsavel.setNome(pessoa.getNome());
        responsavel.setCpf(pessoa.getCpf());
        responsavel.setRg(pessoa.getRg());
        responsavel.setTelefone(pessoa.getTelefone());
        responsavel.setDataNascimento(pessoa.getDtNascimento());

        if (pessoa.getProfissao() != null) {
            responsavel.setProfissao(pessoa.getProfissao().getNome());
        }

        return responsavel;
    }

    private static FamiliaDetalhesResponseDto.Dependente toDependente(Pessoa pessoa){

        FamiliaDetalhesResponseDto.Dependente dependente = new FamiliaDetalhesResponseDto.Dependente();
        dependente.setId(pessoa.getId());
        dependente.setNome(pessoa.getNome());
        dependente.setCpf(pessoa.getCpf());
        dependente.setRg(pessoa.getRg());
        dependente.setTelefone(pessoa.getTelefone());
        dependente.setDataNascimento(pessoa.getDtNascimento());
        dependente.setGrauParentesco(pessoa.getGrauParentesco());

        if (pessoa.getProfissao() != null) {
            dependente.setProfissao(pessoa.getProfissao().getNome());
        }

        return dependente;
    }

}

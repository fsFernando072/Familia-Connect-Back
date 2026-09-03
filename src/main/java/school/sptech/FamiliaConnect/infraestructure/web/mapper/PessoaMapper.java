package school.sptech.FamiliaConnect.infraestructure.web.mapper;

import school.sptech.FamiliaConnect.infraestructure.web.dto.pessoa.PessoaRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.pessoa.PessoaResponseDto;
import school.sptech.FamiliaConnect.domain.entity.Familia;
import school.sptech.FamiliaConnect.domain.entity.GrauParentesco;
import school.sptech.FamiliaConnect.domain.entity.Pessoa;
import school.sptech.FamiliaConnect.domain.entity.Profissao;

import java.util.List;

public class PessoaMapper {

    public static Pessoa toModel(PessoaRequestDto pessoaRequestDto){

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaRequestDto.getNome());
        pessoa.setRg(pessoaRequestDto.getRg());
        pessoa.setCpf(pessoaRequestDto.getCpf());
        pessoa.setDtNascimento(pessoaRequestDto.getDataNascimento());
        pessoa.setResponsavel(pessoaRequestDto.getResponsavel());
        pessoa.setTelefone(pessoaRequestDto.getTelefone());

        GrauParentesco grauParentesco = new GrauParentesco();
        grauParentesco.setGrau(pessoaRequestDto.getGrauParentesco());
        pessoa.setGrauParentesco(grauParentesco);

        Profissao profissao = new Profissao();
        profissao.setNome(pessoaRequestDto.getProfissao());
        pessoa.setProfissao(profissao);

        pessoa.setSexo(pessoaRequestDto.getSexo());

        return pessoa;

    }

    public static PessoaResponseDto toResponse(Pessoa pessoa){

        Familia familiaEntidade = pessoa.getFamilia();
        PessoaResponseDto.PessoaFamilia familia = new PessoaResponseDto.PessoaFamilia();
        familia.setDataCadastro(familiaEntidade.getDataCadastro());
        familia.setFoto(familiaEntidade.getFotoUrl());

        Profissao profissaoEntidade = pessoa.getProfissao();
        PessoaResponseDto.PessoaProfissao profissao = new PessoaResponseDto.PessoaProfissao();
        profissao.setNome(profissaoEntidade.getNome());

        PessoaResponseDto dto = new PessoaResponseDto();

        dto.setId(pessoa.getId());
        dto.setNome(pessoa.getNome());
        dto.setRg(pessoa.getRg());
        dto.setCpf(pessoa.getCpf());
        dto.setDataNascimento(pessoa.getDtNascimento());
        dto.setResponsavel(pessoa.getResponsavel());
        dto.setGrauParentesco(pessoa.getGrauParentesco().getGrau());
        dto.setTelefone(pessoa.getTelefone());
        dto.setSexo(pessoa.getSexo());

        dto.setPessoaFamilia(familia);
        dto.setPessoaProfissao(profissao);

        return dto;

    }

    public static List<PessoaResponseDto> toResponse(List<Pessoa> pessoas) {
        return pessoas.stream()
                .map(PessoaMapper::toResponse)
                .toList();
    }

}

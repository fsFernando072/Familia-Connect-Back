package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.pessoa.PessoaRequestDto;
import school.sptech.FamiliaConnect.dto.pessoa.PessoaResponseDto;
import school.sptech.FamiliaConnect.model.Deficiencia;
import school.sptech.FamiliaConnect.model.Familia;
import school.sptech.FamiliaConnect.model.Pessoa;
import school.sptech.FamiliaConnect.model.Profissao;

import java.util.List;

public class PessoaMapper {

    public static Pessoa toModel(PessoaRequestDto pessoaRequestDto){

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaRequestDto.getNome());
        pessoa.setRg(pessoaRequestDto.getRg());
        pessoa.setCpf(pessoaRequestDto.getCpf());
        pessoa.setDtNascimento(pessoaRequestDto.getDataNascimento());
        pessoa.setTrabalhando(pessoaRequestDto.getTrabalhando());
        pessoa.setResponsavel(pessoaRequestDto.getResponsavel());
        pessoa.setGrauParentesco(pessoaRequestDto.getGrauParentesco());
        pessoa.setTelefone(pessoaRequestDto.getTelefone());

        return pessoa;

    }

    public static PessoaResponseDto toResponse(Pessoa pessoa){

        Deficiencia deficienciaEntidade = new Deficiencia();
        PessoaResponseDto.PessoaDeficiencia deficiencia = new PessoaResponseDto.PessoaDeficiencia();
        deficiencia.setNome(deficienciaEntidade.getNome());

        Familia familiaEntidade = new Familia();
        PessoaResponseDto.PessoaFamilia familia = new PessoaResponseDto.PessoaFamilia();
        familia.setDataCadastro(familiaEntidade.getDataCadastro());
        familia.setFoto(familiaEntidade.getFotoFamilia());

        Profissao profissaoEntidade = new Profissao();
        PessoaResponseDto.PessoaProfissao profissao = new PessoaResponseDto.PessoaProfissao();
        profissao.setNome(profissaoEntidade.getNome());

        PessoaResponseDto dto = new PessoaResponseDto();

        dto.setNome(pessoa.getNome());
        dto.setRg(pessoa.getRg());
        dto.setCpf(pessoa.getCpf());
        dto.setDataNascimento(pessoa.getDtNascimento());
        dto.setTrabalhando(pessoa.getTrabalhando());
        dto.setResponsavel(pessoa.getResponsavel());
        dto.setGrauParentesco(pessoa.getGrauParentesco());
        dto.setTelefone(pessoa.getTelefone());

        dto.setPessoaDeficiencia(deficiencia);
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

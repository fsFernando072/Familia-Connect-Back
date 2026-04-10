package school.sptech.FamiliaConnect.mapper;

import school.sptech.FamiliaConnect.dto.pessoa.PessoaRequestDto;
import school.sptech.FamiliaConnect.dto.pessoa.PessoaResponseDto;
import school.sptech.FamiliaConnect.model.Pessoa;

public class PessoaMapper {

    public static Pessoa toModel(PessoaRequestDto pessoaRequestDto){

        Pessoa pessoa = new Pessoa(
            pessoaRequestDto.getNome(),
                pessoaRequestDto.getRg(),
                pessoaRequestDto.getCpf(),
                pessoaRequestDto.getDataNascimento(),
                pessoaRequestDto.getTrabalhando(),
                pessoaRequestDto.getResponsavel(),
                pessoaRequestDto.getGrauParentesco(),
                pessoaRequestDto.getTelefone()
        );

        return pessoa;

    }

    public static PessoaResponseDto toResponse(Pessoa pessoa){

        PessoaResponseDto pessoaResponseDto = new PessoaResponseDto(
            pessoa.getNome(),
                pessoa.getRg(),
                pessoa.getCpf(),
                pessoa.getDtNascimento(),
                pessoa.getTelefone(),
                pessoa.getTrabalhando(),
                pessoa.getResponsavel(),
                pessoa.getGrauParentesco()
        );

        if(pessoa.getProfissao() != null){
            PessoaResponseDto.PessoaProfissao pessoaProfissao = new PessoaResponseDto.PessoaProfissao(
                    pessoa.getProfissao().getNome()
            );

            pessoaResponseDto.setPessoaProfissao(pessoaProfissao);
        }

        if(pessoa.getDeficiencia() != null){
            PessoaResponseDto.PessoaDeficiencia pessoaDeficiencia = new PessoaResponseDto.PessoaDeficiencia(
                    pessoa.getDeficiencia().getNome()
            );

            pessoaResponseDto.setPessoaDeficiencia(pessoaDeficiencia);
        }

        PessoaResponseDto.PessoaFamilia pessoaFamilia = new PessoaResponseDto.PessoaFamilia(
                pessoa.getFamilia().getFotoFamilia(),
                pessoa.getFamilia().getDataCadastro()
        );

        pessoaResponseDto.setPessoaFamilia(pessoaFamilia);

        return pessoaResponseDto;

    }

}

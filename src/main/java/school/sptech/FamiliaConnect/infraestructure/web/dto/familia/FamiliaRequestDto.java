package school.sptech.FamiliaConnect.infraestructure.web.dto.familia;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import school.sptech.FamiliaConnect.infraestructure.web.dto.endereco.EnderecoRequestDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.pessoa.PessoaRequestDto;

import java.time.LocalDate;
import java.util.List;

public class FamiliaRequestDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "Data de cadastro da família")
    private LocalDate dataCadastro;

    @Schema(description = "Se a família possui integrante PNE")
    private Boolean possuiPrioridade;

    @Schema(description = "Endereço da família")
    @NotNull(message = "O endereço é obrigatório")
    private EnderecoRequestDto endereco;

    @Schema(description = "Responsável pela família")
    @NotNull(message = "O responsável é obrigatório")
    @Valid
    @ConvertGroup(from = Default.class, to = PessoaRequestDto.Responsavel.class)
    private PessoaRequestDto responsavel;

    @Schema(description = "Dependentes da família")
    @Valid
    @ConvertGroup(from = Default.class, to = PessoaRequestDto.Dependente.class)
    private List<PessoaRequestDto> dependentes;

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getPossuiPrioridade() {
        return possuiPrioridade;
    }

    public void setPossuiPrioridade(Boolean possuiPrioridade) {
        this.possuiPrioridade = possuiPrioridade;
    }

    public EnderecoRequestDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoRequestDto endereco) {
        this.endereco = endereco;
    }

    public PessoaRequestDto getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(PessoaRequestDto responsavel) {
        this.responsavel = responsavel;
    }

    public List<PessoaRequestDto> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<PessoaRequestDto> dependentes) {
        this.dependentes = dependentes;
    }
}

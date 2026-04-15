package school.sptech.FamiliaConnect.dto.entrega;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import school.sptech.FamiliaConnect.model.Funcionario;
import school.sptech.FamiliaConnect.model.Pessoa;

import java.time.LocalDate;

public class EntregaRequestDto {

    @NotBlank
    private LocalDate dataEntrega;

    @NotBlank
    private Funcionario funcionario;

    @NotBlank
    private Pessoa pessoa;

    public EntregaRequestDto(){

    }

    public EntregaRequestDto(LocalDate dataEntrega, Funcionario funcionario, Pessoa pessoa) {
        this.dataEntrega = dataEntrega;
        this.funcionario = funcionario;
        this.pessoa = pessoa;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}

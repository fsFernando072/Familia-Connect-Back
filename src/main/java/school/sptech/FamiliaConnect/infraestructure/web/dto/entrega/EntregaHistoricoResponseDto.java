package school.sptech.FamiliaConnect.infraestructure.web.dto.entrega;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Entrega de uma família em um mês, com todos os produtos recebidos")
public class EntregaHistoricoResponseDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    @Schema(description = "ID da família")
    private Integer idFamilia;

    @Schema(description = "Mês da entrega (AAAA-MM)")
    private String mes;

    @Schema(description = "Data da entrega")
    private LocalDate dataDaEntrega;

    @Schema(description = "Produtos entregues, separados por vírgula (ex.: Cesta Básica, Leite ×2)")
    private String produtos;

    private String nomeFamilia;
    private String nomeResponsavel;
    private String telefoneResponsavel;
    private String fotoFamilia;

    // Construtores ----------------------------------------------------------------------------------------------------

    public EntregaHistoricoResponseDto(Integer idFamilia, String mes, LocalDate dataDaEntrega, String produtos, String nomeFamilia, String nomeResponsavel, String telefoneResponsavel, String fotoFamilia) {
        this.idFamilia = idFamilia;
        this.mes = mes;
        this.dataDaEntrega = dataDaEntrega;
        this.produtos = produtos;
        this.nomeFamilia = nomeFamilia;
        this.nomeResponsavel = nomeResponsavel;
        this.telefoneResponsavel = telefoneResponsavel;
        this.fotoFamilia = fotoFamilia;
    }

    // Getters ---------------------------------------------------------------------------------------------------------

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public String getMes() {
        return mes;
    }

    public LocalDate getDataDaEntrega() {
        return dataDaEntrega;
    }

    public String getProdutos() {
        return produtos;
    }

    public String getNomeFamilia() {
        return nomeFamilia;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public String getFotoFamilia() {
        return fotoFamilia;
    }
}

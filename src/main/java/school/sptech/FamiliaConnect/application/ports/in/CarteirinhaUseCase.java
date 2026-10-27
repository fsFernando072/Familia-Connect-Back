package school.sptech.FamiliaConnect.application.ports.in;

import school.sptech.FamiliaConnect.domain.entity.Pessoa;
import school.sptech.FamiliaConnect.domain.entity.carteirinha.ProcessamentoCarteirinha;
import school.sptech.FamiliaConnect.domain.entity.carteirinha.StatusCarteirinha;

public interface CarteirinhaUseCase {

    Integer searchByCarteirinha(String idCarteirinha);
    ProcessamentoCarteirinha criarCarteirinha(Pessoa responsavel, Integer idFamilia);
    ProcessamentoCarteirinha abrirProcessamento(Pessoa responsavel);
    void enviarParaProcessamento(Pessoa responsavel, Integer idFamilia, java.util.UUID correlationId);
    StatusCarteirinha buscarStatusCarteirinha(String correlationId);
    String buscarCarteirinhaPorIdFamilia(Integer idFamilia);

}

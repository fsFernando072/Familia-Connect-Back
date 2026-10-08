package school.sptech.FamiliaConnect.application.ports.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import school.sptech.FamiliaConnect.domain.entity.Entrega;
import school.sptech.FamiliaConnect.infraestructure.web.dto.entrega.EntregaHistoricoResponseDto;
import school.sptech.FamiliaConnect.infraestructure.web.dto.entrega.FamiliaPendenteEntregaResponseDto;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public interface EntregaUseCase {

    List<Entrega> listar();
    Entrega listarPorId(Integer id);
    Entrega salvar(Entrega entrega);
    Entrega atualizar(Integer id, Entrega entrega);
    void deletar(Integer id);

    List<Entrega> salvarLote(Integer idPessoa, Integer idFuncionario, LocalDate dataEntrega, Map<Integer, Integer> quantidadesPorProduto);

    Page<FamiliaPendenteEntregaResponseDto> listarFamiliasPendentes(String nomeResponsavel, YearMonth mes, Pageable pageable);

    Page<EntregaHistoricoResponseDto> listarHistorico(String nomeResponsavel, YearMonth mes, Pageable pageable);

    void deletarPorFamiliaNoMes(Integer idFamilia, YearMonth mes);

}

package school.sptech.FamiliaConnect.application.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.grafana.EntregaMetricasRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.grafana.FamiliaMetricasRepository;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class GrafanaMetricasService {
    private final FamiliaMetricasRepository familias;
    private final EntregaMetricasRepository entregas;

    public GrafanaMetricasService(FamiliaMetricasRepository familias, EntregaMetricasRepository entregas) {
        this.familias = familias;
        this.entregas = entregas;
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> consultar(String id, Long from, Long to, Long familiaId) {
        long fim = to == null ? System.currentTimeMillis() : to;
        long inicio = from == null ? fim - 180L * 24 * 60 * 60 * 1000 : from;
        if (inicio > fim || fim - inicio > 5L * 365 * 24 * 60 * 60 * 1000) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Período inválido");
        }

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Painel não encontrado");
        }

        Timestamp dataInicio = new Timestamp(inicio);
        Timestamp dataFim = new Timestamp(fim);
        switch (id) {
            case "2":
                return familias.painel2();
            case "3":
                return familias.painel3();
            case "4":
                return familias.painel4();
            case "5":
                return entregas.painel5(dataInicio, dataFim);
            case "6":
                return familias.painel6();
            case "7":
                return entregas.painel7(dataInicio, dataFim);
            case "8":
                return entregas.painel8(dataInicio, dataFim);
            case "9":
                return entregas.painel9();
            case "10":
                return entregas.painel10(dataInicio, dataFim);
            case "11":
                return entregas.painel11();
            case "12":
                return entregas.painel12();
            case "13":
                return entregas.painel13();
            case "15":
                return entregas.painel15(dataInicio, dataFim);
            case "16":
                return entregas.painel16(dataInicio, dataFim);
            case "17":
                return entregas.painel17(dataInicio, dataFim);
            case "18":
                return entregas.painel18(dataInicio, dataFim);
            case "19":
                return entregas.painel19(dataInicio, dataFim);
            case "20":
                return entregas.painel20(dataInicio, dataFim);
            case "22":
                return entregas.painel22();
            case "23":
                return entregas.painel23();
            case "24":
                return entregas.painel24(dataInicio, dataFim);
            case "26":
                return entregas.painel26(dataInicio, dataFim);
            case "27":
                return familias.painel27();
            case "28":
                return familias.painel28();
            case "29":
                return familias.painel29();
            case "30":
                return familias.painel30();
            case "31":
                return familias.painel31();
            case "32":
                return familias.painel32();
            case "101":
                validarFamilia(familiaId);
                return entregas.painel101(familiaId, dataInicio, dataFim);
            case "102":
                validarFamilia(familiaId);
                return entregas.painel102(familiaId);
            case "103":
                validarFamilia(familiaId);
                return entregas.painel103(familiaId);
            case "104":
                validarFamilia(familiaId);
                return entregas.painel104(familiaId);
            case "105":
                validarFamilia(familiaId);
                return entregas.painel105(familiaId, dataInicio, dataFim);
            case "106":
                validarFamilia(familiaId);
                return entregas.painel106(familiaId, dataInicio, dataFim);
            case "107":
                validarFamilia(familiaId);
                return entregas.painel107(familiaId, dataInicio, dataFim);
            case "108":
                return familias.painel108(dataInicio, dataFim);
            case "109":
                return familias.painel109(dataInicio, dataFim);
            case "110":
                return familias.painel110();
            case "112":
                return familias.painel112(dataInicio, dataFim);
            case "113":
                return familias.painel113(dataInicio, dataFim);
            default:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Painel não encontrado");
        }
    }

    private void validarFamilia(Long familiaId) {
        if (familiaId == null || familiaId <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Família inválida");
        }
    }
}

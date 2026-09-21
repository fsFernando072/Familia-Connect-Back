package school.sptech.FamiliaConnect.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.application.ports.in.CepUseCase;
import school.sptech.FamiliaConnect.application.ports.out.ConsultaCepPort;
import school.sptech.FamiliaConnect.domain.entity.EnderecoCep;
import school.sptech.FamiliaConnect.domain.exception.CepInvalidoException;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;

@Service
public class CepService implements CepUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CepService.class);
    private static final int TAMANHO_CEP = 8;

    // Depende apenas da porta (ConsultaCepPort), não do adapter concreto do ViaCEP.
    private final ConsultaCepPort consultaCepPort;

    public CepService(ConsultaCepPort consultaCepPort) {
        this.consultaCepPort = consultaCepPort;
    }

    @Override
    public EnderecoCep buscarEnderecoPorCep(String cep) {
        String cepLimpo = somenteDigitos(cep);

        if (cepLimpo.length() != TAMANHO_CEP) {
            throw new CepInvalidoException("CEP inválido: deve conter " + TAMANHO_CEP + " dígitos.");
        }

        LOGGER.info("Consultando endereço para o CEP {}", cepLimpo);

        EnderecoCep enderecoCep = consultaCepPort.consultar(cepLimpo);

        if (enderecoCep == null) {
            throw new EntidadeNaoEncontradaException("Nenhum endereço encontrado para o CEP informado.");
        }

        return enderecoCep;
    }

    private String somenteDigitos(String valor) {
        return valor == null ? "" : valor.replaceAll("\\D", "");
    }
}

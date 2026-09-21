package school.sptech.FamiliaConnect.infraestructure.web.adapter;

import org.springframework.stereotype.Component;
import school.sptech.FamiliaConnect.application.ports.out.ConsultaCepPort;
import school.sptech.FamiliaConnect.domain.entity.EnderecoCep;
import school.sptech.FamiliaConnect.infraestructure.web.client.ViaCepClient;
import school.sptech.FamiliaConnect.infraestructure.web.dto.cep.ViaCepResponseDto;

/**
 * Adapter (GoF): adapta o {@link ViaCepClient} — cuja interface e DTO são moldados
 * pelo formato da API externa do ViaCEP — para o contrato {@link ConsultaCepPort},
 * que é o que a camada de aplicação ({@code CepService}) realmente conhece.
 * <p>
 * Se um dia o ViaCEP for substituído por outro provedor (ex: BrasilAPI), basta criar
 * outro {@code @Component} implementando {@link ConsultaCepPort} — {@code CepService}
 * e o restante da aplicação não precisam mudar.
 */
@Component
public class ViaCepAdapter implements ConsultaCepPort {

    private final ViaCepClient viaCepClient;

    public ViaCepAdapter(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    @Override
    public EnderecoCep consultar(String cepLimpo) {
        ViaCepResponseDto resposta = viaCepClient.buscarPorCep(cepLimpo);

        if (resposta == null || Boolean.TRUE.equals(resposta.getErro())) {
            return null;
        }

        return paraDominio(resposta);
    }

    private EnderecoCep paraDominio(ViaCepResponseDto resposta) {
        EnderecoCep enderecoCep = new EnderecoCep();
        enderecoCep.setCep(resposta.getCep());
        enderecoCep.setLogradouro(resposta.getLogradouro());
        enderecoCep.setComplemento(resposta.getComplemento());
        enderecoCep.setBairro(resposta.getBairro());
        enderecoCep.setLocalidade(resposta.getLocalidade());
        enderecoCep.setUf(resposta.getUf());
        return enderecoCep;
    }
}

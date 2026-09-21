package school.sptech.FamiliaConnect.application.ports.out;

import school.sptech.FamiliaConnect.domain.entity.EnderecoCep;

/**
 * Porta de saída (padrão Adapter) para consulta de endereço a partir de um CEP.
 * <p>
 * A camada de aplicação ({@link school.sptech.FamiliaConnect.application.service.CepService})
 * conhece apenas este contrato — não sabe (nem precisa saber) que hoje quem o implementa é
 * uma chamada HTTP para o ViaCEP. Se o provedor de CEP mudar no futuro, basta criar um novo
 * Adapter implementando esta interface, sem alterar nada na camada de aplicação.
 */
public interface ConsultaCepPort {

    /**
     * @param cepLimpo CEP já validado e contendo somente dígitos (8 caracteres)
     * @return o endereço encontrado, ou {@code null} caso o CEP não exista
     */
    EnderecoCep consultar(String cepLimpo);

}

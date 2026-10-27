package school.sptech.FamiliaConnect.infraestructure.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import school.sptech.FamiliaConnect.application.ports.out.CarteirinhaPortOut;
import school.sptech.FamiliaConnect.infraestructure.config.RabbitMqConfig;

import java.util.UUID;
import school.sptech.FamiliaConnect.infraestructure.producer.dto.CarteirinhaMensagemDto;

@Component
public class CarteirinhaProducer implements CarteirinhaPortOut {

    private final RabbitTemplate rabbitTemplate;

    public CarteirinhaProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviar(CarteirinhaMensagemDto mensagem, UUID correlationId) {
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.EXCHANGE_CARTEIRINHAS,
                RabbitMqConfig.BINDING_CARTEIRINHAS,
                mensagem,
                message -> {
                    message.getMessageProperties().setHeader("correlationIdRedis", correlationId.toString());
                    return message;
                });
    }

    public static String formatarCpf(String cpf) {
        if (cpf == null) {
            return null;
        }
        String digitos = cpf.replaceAll("\\D", "");
        if (digitos.length() != 11) {
            return cpf;
        }
        return digitos.substring(0, 3) + "." + digitos.substring(3, 6) + "."
                + digitos.substring(6, 9) + "-" + digitos.substring(9);
    }
}

package school.sptech.FamiliaConnect.infraestructure.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import school.sptech.FamiliaConnect.application.service.RedisService;
import school.sptech.FamiliaConnect.domain.entity.carteirinha.StatusCarteirinha;
import school.sptech.FamiliaConnect.infraestructure.config.RabbitMqConfig;

import java.util.UUID;

@Component
public class CarteirinhaRespostaConsumer {

    private static final Logger log = LoggerFactory.getLogger(CarteirinhaRespostaConsumer.class);

    private final RedisService redisService;

    public CarteirinhaRespostaConsumer(RedisService redisService) {
        this.redisService = redisService;
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_RESPOSTA_CARTEIRINHAS)
    public void onMessage(@Header(value = "correlationIdRedis", required = false) String correlationIdRedis,
                          StatusCarteirinha status) {
        if (correlationIdRedis == null || correlationIdRedis.isBlank()) {
            log.error("[onMessage] Mensagem de resposta sem header correlationIdRedis, descartando");
            return;
        }
        try {
            redisService.atualizarStatusCarteirinha(UUID.fromString(correlationIdRedis), status);
            log.info("[onMessage] Status da carteirinha {} atualizado no redis: {}", correlationIdRedis, status.getStatus());
        } catch (IllegalArgumentException e) {
            log.error("[onMessage] correlationIdRedis inválido: {}", correlationIdRedis);
        }
    }
}

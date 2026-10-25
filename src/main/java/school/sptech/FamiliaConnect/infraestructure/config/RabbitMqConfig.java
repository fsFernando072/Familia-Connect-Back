package school.sptech.FamiliaConnect.infraestructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String EXCHANGE_CARTEIRINHAS = "familia.carteirinhas";
    public static final String BINDING_CARTEIRINHAS = "binding.carteirinhas";
    public static final String QUEUE_CARTEIRINHAS = "queue.carteirinhas";
    public static final String QUEUE_RESPOSTA_CARTEIRINHAS = "resposta.carteirinhas";
    public static final String ROUTING_RESPOSTA_CARTEIRINHAS = "routing.resposta";

    @Bean
    public DirectExchange carteirinhasExchange() {
        return new DirectExchange(EXCHANGE_CARTEIRINHAS);
    }

    @Bean
    public Queue carteirinhasQueue() {
        return new Queue(QUEUE_CARTEIRINHAS, true);
    }

    @Bean
    public Binding carteirinhasBinding(Queue carteirinhasQueue, DirectExchange carteirinhasExchange) {
        return BindingBuilder.bind(carteirinhasQueue).to(carteirinhasExchange).with(BINDING_CARTEIRINHAS);
    }

    @Bean
    public Queue respostaCarteirinhasQueue() {
        return new Queue(QUEUE_RESPOSTA_CARTEIRINHAS, true);
    }

    @Bean
    public Binding respostaCarteirinhasBinding(Queue respostaCarteirinhasQueue, DirectExchange carteirinhasExchange) {
        return BindingBuilder.bind(respostaCarteirinhasQueue).to(carteirinhasExchange).with(ROUTING_RESPOSTA_CARTEIRINHAS);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}

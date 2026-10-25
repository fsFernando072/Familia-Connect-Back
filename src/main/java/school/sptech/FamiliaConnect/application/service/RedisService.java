package school.sptech.FamiliaConnect.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.domain.entity.carteirinha.ProcessamentoCarteirinha;
import school.sptech.FamiliaConnect.domain.entity.carteirinha.StatusCarteirinha;
import school.sptech.FamiliaConnect.domain.exception.DadoNaoGeradoException;

import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private static String prefixTicket = "ticket:";

    public ProcessamentoCarteirinha createTicketRedis(String nomeResponsavel) {
        UUID correlationId = UUID.randomUUID();
        if(correlationId == null) throw new DadoNaoGeradoException("Falha ao gerar correlationId do redis");

        String chave = prefixTicket + correlationId;
        StatusCarteirinha statusCarteirinha = new StatusCarteirinha("PROCESSANDO", "Processamento da carteirinha em andamento", nomeResponsavel);

        redisTemplate.opsForValue().set(chave, statusCarteirinha, Duration.ofMinutes(5));
        return new ProcessamentoCarteirinha(statusCarteirinha.getStatus(), correlationId);
    }

    public void atualizarStatusCarteirinha(UUID correlationId, StatusCarteirinha statusCarteirinha) {
        String chave = prefixTicket + correlationId;

        // O processador responde só com status e mensagem: mantém o nome que foi guardado ao cadastrar.
        if (statusCarteirinha.getNomeResponsavel() == null) {
            Object atual = redisTemplate.opsForValue().get(chave);
            if (atual != null) {
                statusCarteirinha.setNomeResponsavel(objectMapper.convertValue(atual, StatusCarteirinha.class).getNomeResponsavel());
            }
        }

        redisTemplate.opsForValue().set(chave, statusCarteirinha, Duration.ofMinutes(5));
    }

    public StatusCarteirinha getStatusCarteirinha(UUID correlationId) {
        String chave = prefixTicket + correlationId;
        Object valor = redisTemplate.opsForValue().get(chave);
        if(valor == null) throw new DadoNaoGeradoException("Falha ao buscar status da carteirinha no redis");
        return objectMapper.convertValue(valor, StatusCarteirinha.class);
    }

}

package school.sptech.FamiliaConnect.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.domain.entity.carteirinha.ProcessamentoCarteirinha;
import school.sptech.FamiliaConnect.domain.entity.carteirinha.StatusCarteirinha;
import school.sptech.FamiliaConnect.domain.exception.DadoNaoGeradoException;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public ProcessamentoCarteirinha createTicketRedis(Integer idFamilia, String nomeResponsavel) {
        UUID correlationId = generateIdRedis(idFamilia, nomeResponsavel);
        if(correlationId == null) throw new DadoNaoGeradoException("Falha ao gerar correlationId do redis");

        String chave = "ticket:"+correlationId;
        StatusCarteirinha statusCarteirinha = new StatusCarteirinha("PROCESSANDO", "Processamento da carteirinha em andamento");

        redisTemplate.opsForValue().set(chave, statusCarteirinha, Duration.ofMinutes(5));
        return new ProcessamentoCarteirinha(statusCarteirinha.getStatus(), correlationId);
    }

    private UUID generateIdRedis(Integer idFamilia, String nomeResponsavel) {
        String mergeString = String.valueOf(idFamilia)+nomeResponsavel;
        return UUID.fromString(mergeString);
    }

}

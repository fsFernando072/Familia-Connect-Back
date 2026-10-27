package school.sptech.FamiliaConnect.application.service;

import org.springframework.stereotype.Service;
import school.sptech.FamiliaConnect.application.ports.in.CarteirinhaUseCase;
import school.sptech.FamiliaConnect.domain.entity.Familia;
import school.sptech.FamiliaConnect.domain.entity.Pessoa;
import school.sptech.FamiliaConnect.domain.entity.carteirinha.ProcessamentoCarteirinha;
import school.sptech.FamiliaConnect.domain.entity.carteirinha.StatusCarteirinha;
import school.sptech.FamiliaConnect.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.FamiliaRepository;
import school.sptech.FamiliaConnect.infraestructure.producer.CarteirinhaProducer;
import school.sptech.FamiliaConnect.infraestructure.producer.dto.CarteirinhaMensagemDto;

import java.util.UUID;

@Service
public class CarteirinhaService implements CarteirinhaUseCase {

    private final FamiliaRepository familiaRepository;
    private final CarteirinhaProducer carteirinhaProducer;

    private final RedisService redisService;

    public CarteirinhaService(FamiliaRepository familiaRepository, CarteirinhaProducer carteirinhaProducer, RedisService redisService) {
        this.familiaRepository = familiaRepository;
        this.carteirinhaProducer = carteirinhaProducer;
        this.redisService = redisService;
    }

    public ProcessamentoCarteirinha criarCarteirinha(Pessoa responsavel, Integer idFamilia) {
        if(responsavel == null) {
            responsavel = familiaRepository.findByIdFamilia(idFamilia)
                    .filter(integrantes -> !integrantes.isEmpty())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("A família com o id não foi encontrada"))
                    .getFirst();
        }
        ProcessamentoCarteirinha dadosCarteirinha = abrirProcessamento(responsavel);
        enviarParaProcessamento(responsavel, idFamilia, dadosCarteirinha.getCorrelationId());

        return dadosCarteirinha;
    }

    // Gera o ticket (correlationId) no Redis, sem enviar nada ao processador ainda.
    public ProcessamentoCarteirinha abrirProcessamento(Pessoa responsavel) {
        return redisService.createTicketRedis(responsavel.getNome());
    }

    public void enviarParaProcessamento(Pessoa responsavel, Integer idFamilia, UUID correlationId) {
        CarteirinhaMensagemDto mensagem = new CarteirinhaMensagemDto(
                idFamilia,
                responsavel.getNome(),
                CarteirinhaProducer.formatarCpf(responsavel.getCpf()),
                responsavel.getTelefone());

        carteirinhaProducer.enviar(mensagem, correlationId);
    }

    public Integer searchByCarteirinha(String idCarteirinha) {
        Familia familia = familiaRepository.findByIdCarteirinha(idCarteirinha)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("A família com a carteirinha informada não foi encontrada"));

        return familia.getId();
    }

    public String buscarCarteirinhaPorIdFamilia(Integer idFamilia) {
        return familiaRepository.findIdCarteirinhaByIdFamilia(idFamilia)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Carteirinha não encontrada para a família com id: " + idFamilia));
    }

    public StatusCarteirinha buscarStatusCarteirinha(String correlationId) {
        StatusCarteirinha status = redisService.getStatusCarteirinha(UUID.fromString(correlationId));
        if (status == null) {
            throw new EntidadeNaoEncontradaException("Status da carteirinha não encontrado para a família com id: " + correlationId);
        }
        return status;
    }
}

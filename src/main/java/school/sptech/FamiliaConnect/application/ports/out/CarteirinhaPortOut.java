package school.sptech.FamiliaConnect.application.ports.out;

import school.sptech.FamiliaConnect.infraestructure.producer.dto.CarteirinhaMensagemDto;

import java.util.UUID;

public interface CarteirinhaPortOut {

    void enviar(CarteirinhaMensagemDto mensagemDto, UUID correlationId);
}

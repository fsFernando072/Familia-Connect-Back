package school.sptech.FamiliaConnect.infraestructure.producer.dto;

public record CarteirinhaMensagemDto(
        Integer idFamilia,
        String nomeResponsavel,
        String cpfResponsavel,
        String telefone
) {
}

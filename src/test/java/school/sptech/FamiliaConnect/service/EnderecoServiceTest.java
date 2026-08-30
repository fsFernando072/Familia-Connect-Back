package school.sptech.FamiliaConnect.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.FamiliaConnect.application.service.EnderecoService;
import school.sptech.FamiliaConnect.domain.entity.Endereco;
import school.sptech.FamiliaConnect.domain.entity.Estado;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.EnderecoRepository;
import school.sptech.FamiliaConnect.infraestructure.persistence.repository.EstadoRepository;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @Mock
    EnderecoRepository enderecoRepository;

    @Mock
    EstadoRepository estadoRepository;

    @InjectMocks
    EnderecoService enderecoService;

    @Nested
    @DisplayName("Deve cadastrar endereços corretamente")
    class cadastrar {

        @Test
        @DisplayName("Cadastrar endereço corretamente")
        void cadastrarEndereco() {
            Estado estado = new Estado();
            estado.setId(1);
            estado.setNome("São Paulo");

            Endereco endereco = new Endereco();
            endereco.setCep("08490-000");
            endereco.setLogradouro("Rua Teste");
            endereco.setNumero("10");
            endereco.setBairro("Itaquera");
            endereco.setCidade("São Paulo");
            endereco.setEstado(estado);

            Mockito.when(enderecoRepository.save(Mockito.any(Endereco.class)))
                    .thenReturn(endereco);

            Mockito.when(estadoRepository.findById(1))
                    .thenReturn(Optional.of(estado));

            Endereco resultado = enderecoService.salvar(endereco);

            Assertions.assertEquals(endereco.getId(), resultado.getId());
            Assertions.assertEquals(endereco.getCep(), resultado.getCep());
            Assertions.assertEquals(endereco.getLogradouro(), resultado.getLogradouro());
            Assertions.assertEquals(endereco.getNumero(), resultado.getNumero());
            Assertions.assertEquals(endereco.getBairro(), resultado.getBairro());
            Assertions.assertEquals(endereco.getCidade(), resultado.getCidade());
            Assertions.assertEquals(endereco.getEstado(), resultado.getEstado());
        }
    }
}
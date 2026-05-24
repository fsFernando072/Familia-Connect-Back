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
import school.sptech.FamiliaConnect.dto.endereco.EnderecoRequestDto;
import school.sptech.FamiliaConnect.mapper.EnderecoMapper;
import school.sptech.FamiliaConnect.model.Endereco;
import school.sptech.FamiliaConnect.model.Estado;
import school.sptech.FamiliaConnect.repository.EnderecoRepository;
import school.sptech.FamiliaConnect.repository.EstadoRepository;

import java.util.ArrayList;
import java.util.List;
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

            EnderecoRequestDto enderecoRequestDto = new EnderecoRequestDto();
            enderecoRequestDto.setCep("08490-000");
            enderecoRequestDto.setLogradouro("Rua Teste");
            enderecoRequestDto.setNumero(10);
            enderecoRequestDto.setBairro("Itaquera");
            enderecoRequestDto.setCidade("São Paulo");
            enderecoRequestDto.setEstadoId(estado.getId());

            Mockito.when(enderecoRepository.save(Mockito.any(Endereco.class)))
                    .thenReturn(EnderecoMapper.toModel(enderecoRequestDto));

            Mockito.when(estadoRepository.findById(1))
                    .thenReturn(Optional.of(estado));

            Endereco resultado = enderecoService.salvar(enderecoRequestDto);

            Assertions.assertEquals(EnderecoMapper.toModel(enderecoRequestDto).getId(), resultado.getId());
            Assertions.assertEquals(EnderecoMapper.toModel(enderecoRequestDto).getCep(), resultado.getCep());
            Assertions.assertEquals(EnderecoMapper.toModel(enderecoRequestDto).getLogradouro(), resultado.getLogradouro());
            Assertions.assertEquals(EnderecoMapper.toModel(enderecoRequestDto).getNumero(), resultado.getNumero());
            Assertions.assertEquals(EnderecoMapper.toModel(enderecoRequestDto).getBairro(), resultado.getBairro());
            Assertions.assertEquals(EnderecoMapper.toModel(enderecoRequestDto).getCidade(), resultado.getCidade());
            Assertions.assertEquals(EnderecoMapper.toModel(enderecoRequestDto).getEstado(), resultado.getEstado());
        }
    }
}
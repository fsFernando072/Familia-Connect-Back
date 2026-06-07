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
import school.sptech.FamiliaConnect.dto.CargoHasAcesso.CargoHasAcessoRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.CargoHasAcessoMapper;
import school.sptech.FamiliaConnect.model.Acesso;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.model.CargoHasAcesso;
import school.sptech.FamiliaConnect.repository.AcessoRepository;
import school.sptech.FamiliaConnect.repository.CargoHasAcessoRepository;
import school.sptech.FamiliaConnect.repository.CargoRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CargoHasAcessoServiceTest {
    @Mock
    CargoRepository cargoRepository;
    @Mock
    AcessoRepository acessoRepository;
    @Mock
    CargoHasAcessoRepository cargoHasAcessoRepository;

    @InjectMocks
    CargoHasAcessoService cargoHasAcessoService;

    @Nested
    @DisplayName("Deve cadastrar cargoHasAcesso corretamente")
    class cadastrar {

        @Test
        @DisplayName("Cadastrar CargoHasAcesso corretamente")
        void cadastrarCargoHasAcesso() {

            Cargo cargo = new Cargo();
            cargo.setId(1);

            Acesso acesso = new Acesso();
            acesso.setId(1);

            CargoHasAcesso dto =
                    new CargoHasAcesso();

            dto.setCargo(cargo);
;           dto.setAcesso(acesso);

            Mockito.when(cargoRepository.findById(1))
                    .thenReturn(Optional.of(cargo));

            Mockito.when(acessoRepository.findById(1))
                    .thenReturn(Optional.of(acesso));


            Mockito.when(cargoHasAcessoRepository.save(Mockito.any(CargoHasAcesso.class)))
                    .thenReturn(dto);

            CargoHasAcesso resultado =
                    cargoHasAcessoService.cadastrar(dto);


            Assertions.assertEquals(cargo.getId(), resultado.getCargo().getId());
            Assertions.assertEquals(acesso.getId(), resultado.getAcesso().getId());

        }
    }
    @Nested
    @DisplayName("Deve listar cargoHasAcesso corretamente")
    class listar {

        @Test
        @DisplayName("Listar cargoHasAcesso")
        void listarCargoHasAcesso() {

            List<CargoHasAcesso> lista = new ArrayList<>();

            CargoHasAcesso cargoHasAcesso = new CargoHasAcesso();
            cargoHasAcesso.setId(1);

            lista.add(cargoHasAcesso);

            Mockito.when(cargoHasAcessoRepository.findAll())
                    .thenReturn(lista);

            List<CargoHasAcesso> resultado =
                    cargoHasAcessoService.listar();

            Assertions.assertIterableEquals(lista, resultado);
        }
    }
    @Nested
    @DisplayName("Deve buscar CargoHasAcesso corretamente")
    class buscar {

        @Test
        @DisplayName("Buscar CargoHasAcesso por id")
        void buscarPorId() {

            Cargo cargo = new Cargo();
            cargo.setId(1);

            Acesso acesso = new Acesso();
            acesso.setId(1);

            CargoHasAcesso cargoHasAcesso = new CargoHasAcesso();
            cargoHasAcesso.setId(1);
            cargoHasAcesso.setCargo(cargo);
            cargoHasAcesso.setAcesso(acesso);

            Mockito.when(cargoHasAcessoRepository.findById(1))
                    .thenReturn(Optional.of(cargoHasAcesso));

            CargoHasAcesso resultado = cargoHasAcessoService.buscarPorId(1);

            Assertions.assertEquals(cargoHasAcesso.getId(), resultado.getId());
            Assertions.assertEquals(cargo.getId(), resultado.getCargo().getId());
            Assertions.assertEquals(acesso.getId(), resultado.getAcesso().getId());
        }

        @Test
        @DisplayName("Retornar exception ao buscar id inexistente")
        void buscarIdInexistente() {

            Mockito.when(cargoHasAcessoRepository.findById(1))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> cargoHasAcessoService.buscarPorId(1)
            );
        }
    }
    @Nested
    @DisplayName("Deve atualizar CargoHasAcesso corretamente")
    class atualizar {

        @Test
        @DisplayName("Atualizar CargoHasAcesso existente")
        void atualizarCargoHasAcesso() {

            Integer id = 1;

            Cargo cargo = new Cargo();
            cargo.setId(1);

            Acesso acesso = new Acesso();
            acesso.setId(1);


            CargoHasAcesso dto =
                    new CargoHasAcesso();

            dto.setCargo(cargo);
            dto.setAcesso(acesso);

            CargoHasAcesso cargoHasAcesso = new CargoHasAcesso();
            cargoHasAcesso.setId(id);
            cargoHasAcesso.setCargo(cargo);
            cargoHasAcesso.setAcesso(acesso);

            Mockito.when(cargoHasAcessoRepository.existsById(id))
                    .thenReturn(true);

            Mockito.when(cargoRepository.findById(1))
                    .thenReturn(Optional.of(cargo));

            Mockito.when(acessoRepository.findById(1))
                    .thenReturn(Optional.of(acesso));


            Mockito.when(
                    cargoHasAcessoRepository.save(Mockito.any(CargoHasAcesso.class))).
                    thenReturn(cargoHasAcesso);

            CargoHasAcesso resultado = cargoHasAcessoService.atualizar(id, dto);

            Assertions.assertEquals(id, resultado.getId());
            Assertions.assertEquals(cargo.getId(), resultado.getCargo().getId());
            Assertions.assertEquals(acesso.getId(), resultado.getAcesso().getId());

        }

        @Test
        @DisplayName("Retornar exception ao atualizar inexistente")
        void atualizarInexistente() {

            Integer id = 1;

            CargoHasAcesso dto =
                    new CargoHasAcesso();

            Mockito.when(cargoHasAcessoRepository.existsById(id))
                    .thenReturn(false);

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> cargoHasAcessoService.atualizar(id, dto)
            );
        }
    }
    @Nested
    @DisplayName("Deve deletar CargoHasAcesso corretamente")
    class deletar {

        @Test
        @DisplayName("Deletar CargoHasAcesso existente")
        void deletarCargoHasAcesso() {

            Integer id = 1;

            Mockito.when(cargoHasAcessoRepository.existsById(id))
                    .thenReturn(true);

            Assertions.assertDoesNotThrow(
                    () -> cargoHasAcessoService.deletar(id));

            Mockito.verify(cargoHasAcessoRepository, Mockito.times(1)).deleteById(id);
        }

        @Test
        @DisplayName("Retornar exception ao deletar inexistente")
        void deletarInexistente() {

            Integer id = 1;

            Mockito.when(cargoHasAcessoRepository.existsById(id))
                    .thenReturn(false);

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> cargoHasAcessoService.deletar(id)
            );
        }
    }
}
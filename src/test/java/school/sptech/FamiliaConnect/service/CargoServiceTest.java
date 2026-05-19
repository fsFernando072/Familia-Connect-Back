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
import school.sptech.FamiliaConnect.dto.Cargo.CargoRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeJaCadastradaException;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.mapper.CargoMapper;
import school.sptech.FamiliaConnect.model.Cargo;
import school.sptech.FamiliaConnect.repository.CargoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CargoServiceTest {

    @Mock
    CargoRepository cargoRepository;

    @InjectMocks
    CargoService cargoService;

    @Nested
    @DisplayName("Deve realizar os cadastramento de cargos corretamente")
    class cadastrar {
        @Test
        @DisplayName("Deve cadastrar o cargo corretamente")
        void cadastrarCargo(){
            CargoRequestDto cargoRequestDto = new CargoRequestDto();
            cargoRequestDto.setNome("nomeCargo");

            Mockito.when(cargoRepository.save(Mockito.any(Cargo.class)))
                    .thenReturn(CargoMapper.toModel(cargoRequestDto));

            Cargo resultado = cargoService.cadastrar(cargoRequestDto);

            Assertions.assertEquals(CargoMapper.toModel(cargoRequestDto).getNome(), resultado.getNome());
        }

        @Test
        @DisplayName("Deve retornar EntidadeJaCadastradaException")
        void cargoDuplicado(){
            String nome = "cargo1";

            CargoRequestDto cargoRequestDto = new CargoRequestDto();

            cargoRequestDto.setNome("cargo1");

            Mockito.when(cargoRepository.findByNome(nome))
                    .thenReturn(Optional.of(CargoMapper.toModel(cargoRequestDto)));

            Assertions.assertThrows(
                    EntidadeJaCadastradaException.class,
                    () -> cargoService.cadastrar(cargoRequestDto));
        }
    }

    @Nested
    @DisplayName("Deve listar os cargos corretamente")
    class listar {
        @Test
        @DisplayName("Deve retornar uma lista com todos os cargos")
        void retornarListaComTodosCargos(){
            List<Cargo> cargos = new ArrayList<>();

            Cargo cargo = new Cargo();

            cargo.setId(1);
            cargo.setNome("cargo1");

            cargos.add(cargo);

            Mockito.when(cargoRepository.findAll())
                    .thenReturn(cargos);
            List<Cargo> resultado = cargoService.listar();

            Assertions.assertIterableEquals(cargos, resultado);
        }

        @Test
        @DisplayName("Deve retornar uma lista com todos os cargos")
        void retornarListaVazia(){
            List<Cargo> cargos = new ArrayList<>();

            Mockito.when(cargoRepository.findAll())
                    .thenReturn(cargos);
            List<Cargo> resultado = cargoService.listar();

            Assertions.assertIterableEquals(cargos, resultado);
        }


    }

    @Nested
    @DisplayName("Deve buscar os cargos")
    class buscar{
        @Test
        @DisplayName("Deve retornar um cargo que existe através do seu ID")
        void retornarAcessoPorId(){

            Cargo cargo = new Cargo();

            cargo.setId(1);
            cargo.setNome("cargo1");

            Mockito.when(cargoRepository.findById(1))
                    .thenReturn(Optional.of(cargo));
            Cargo resultado = cargoService.buscarPorId(1);

            Assertions.assertEquals(1, resultado.getId());
            Assertions.assertEquals("cargo1", resultado.getNome());

        }

        @Test
        @DisplayName("Deve EntidadeNaoEncontradaExceptio quando buscar por ID não encotrar nada")
        void retornarExceptionCargoNaoEncontrado(){

            Integer id = 1;
            Optional<Cargo> optional = Optional.empty();

            Mockito.when(cargoRepository.findById(id))
                    .thenReturn(optional);

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> cargoService.buscarPorId(id)
            );
        }
    }

    @Nested
    @DisplayName("Deve realizar as atualizações corretamente")
    class atualizar {
        @Test
        @DisplayName("Deve atualizar um cargo existente")
        void atualizarCargo() {

            Integer id = 1;

            CargoRequestDto cargoRequestDto = new CargoRequestDto();
            cargoRequestDto.setNome("cargoAtualizado");

            Cargo cargoAtualizado = CargoMapper.toModel(cargoRequestDto);
            cargoAtualizado.setId(id);

            Mockito.when(cargoRepository.existsById(id))
                    .thenReturn(true);

            Mockito.when(cargoRepository.save(Mockito.any(Cargo.class)))
                    .thenReturn(cargoAtualizado);

            Cargo resultado = cargoService.atualizar(id, cargoRequestDto);

            Assertions.assertEquals(id, resultado.getId());
            Assertions.assertEquals("cargoAtualizado", resultado.getNome());
        }

        @Test
        @DisplayName("Deve retornar EntidadeNaoEncontradaException ao atualizar cargo inexistente")
        void atualizarCargoInexistente() {

            Integer id = 1;

            CargoRequestDto cargoRequestDto = new CargoRequestDto();
            cargoRequestDto.setNome("cargoAtualizado");

            Mockito.when(cargoRepository.existsById(id))
                    .thenReturn(false);

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> cargoService.atualizar(id, cargoRequestDto)
            );
        }

    }

    @Nested
    @DisplayName("Deve deletar corretamente")
    class deletar {
        @Test
        @DisplayName("Deve deletar um cargo existente")
        void deletarCargo() {

            Integer id = 1;

            Mockito.when(cargoRepository.existsById(id))
                    .thenReturn(true);

            Assertions.assertDoesNotThrow(() -> cargoService.deletar(id));

            Mockito.verify(cargoRepository, Mockito.times(1))
                    .deleteById(id);
        }

        @Test
        @DisplayName("Deve retornar EntidadeNaoEncontradaException ao deletar cargo inexistente")
        void deletarCargoInexistente() {

            Integer id = 1;

            Mockito.when(cargoRepository.existsById(id))
                    .thenReturn(false);

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> cargoService.deletar(id)
            );
        }
    }
}
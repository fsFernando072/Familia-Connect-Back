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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import school.sptech.FamiliaConnect.dto.endereco.EnderecoRequestDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaListResponseDto;
import school.sptech.FamiliaConnect.dto.familia.FamiliaRequestDto;
import school.sptech.FamiliaConnect.dto.pessoa.PessoaRequestDto;
import school.sptech.FamiliaConnect.exception.EntidadeNaoEncontradaException;
import school.sptech.FamiliaConnect.model.Arquivo;
import school.sptech.FamiliaConnect.model.Endereco;
import school.sptech.FamiliaConnect.model.Familia;
import school.sptech.FamiliaConnect.model.Pessoa;
import school.sptech.FamiliaConnect.repository.EnderecoRepository;
import school.sptech.FamiliaConnect.repository.EntregaRepository;
import school.sptech.FamiliaConnect.repository.FamiliaRepository;
import school.sptech.FamiliaConnect.repository.PessoaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FamiliaServiceTest {

    @Mock
    FamiliaRepository familiaRepository;

    @Mock
    EnderecoRepository enderecoRepository;

    @Mock
    PessoaRepository pessoaRepository;

    @Mock
    EntregaRepository entregaRepository;

    @Mock
    EnderecoService enderecoService;

    @Mock
    PessoaService pessoaService;

    @InjectMocks
    FamiliaService familiaService;

    private FamiliaRequestDto criarFamiliaRequestDto() {
        EnderecoRequestDto enderecoDto = new EnderecoRequestDto();
        enderecoDto.setCep("08020-000");
        enderecoDto.setBairro("Itaquera");
        enderecoDto.setLogradouro("Rua Macapá");
        enderecoDto.setNumero(1290);
        enderecoDto.setComplemento("Apartamento 20");
        enderecoDto.setCidade("São Paulo");
        enderecoDto.setEstadoId(1);

        PessoaRequestDto responsavelDto = new PessoaRequestDto();
        responsavelDto.setNome("Paola Ferreira");
        responsavelDto.setCpf("35012387698");
        responsavelDto.setRg("368878769");
        responsavelDto.setDataNascimento(LocalDate.of(1992, 7, 13));
        responsavelDto.setTelefone("11998439876");

        PessoaRequestDto dependenteDto = new PessoaRequestDto();
        dependenteDto.setNome("Maria Ferreira");
        dependenteDto.setDataNascimento(LocalDate.of(2010, 7, 19));
        dependenteDto.setGrauParentesco("Filha(o)");

        FamiliaRequestDto dto = new FamiliaRequestDto();
        dto.setDataCadastro(LocalDate.now());
        dto.setPossuiPrioridade(false);
        dto.setEndereco(enderecoDto);
        dto.setResponsavel(responsavelDto);
        dto.setDependentes(List.of(dependenteDto));

        return dto;
    }

    @Nested
    @DisplayName("Deve salvar a família corretamente")
    class salvar {

        @Test
        @DisplayName("Deve salvar família, responsável e dependentes com sucesso")
        void salvarFamilia() {
            Endereco endereco = new Endereco();
            endereco.setId(1);

            Familia familiaSalva = new Familia();
            familiaSalva.setId(1);
            familiaSalva.setEndereco(endereco);

            Mockito.when(enderecoService.salvar(Mockito.any(Endereco.class)))
                    .thenReturn(endereco);

            Mockito.when(familiaRepository.save(Mockito.any(Familia.class)))
                    .thenReturn(familiaSalva);

            Mockito.when(pessoaService.salvar(Mockito.any(Pessoa.class)))
                    .thenAnswer(invocation -> invocation.getArgument(0));

            Familia resultado = familiaService.salvar(criarFamiliaRequestDto());

            Assertions.assertEquals(1, resultado.getId());
            Mockito.verify(pessoaService, Mockito.times(2)).salvar(Mockito.any(Pessoa.class));
        }
    }

    @Nested
    @DisplayName("Deve retornar listas das famílias corretamente")
    class listar {

        @Test
        @DisplayName("Deve retornar página com todas as famílias")
        void retornarPaginaComTodasFamilias() {
            Pageable pageable = PageRequest.of(0, 10);
            FamiliaListResponseDto item = Mockito.mock(FamiliaListResponseDto.class);
            Page<FamiliaListResponseDto> page = new PageImpl<>(List.of(item), pageable, 1);

            Mockito.when(familiaRepository.findAllCustomized(pageable))
                    .thenReturn(page);

            Page<FamiliaListResponseDto> resultado = familiaService.listar(pageable);

            Assertions.assertEquals(1, resultado.getTotalElements());
        }

        @Test
        @DisplayName("Deve retornar página vazia caso não exista nenhuma família")
        void retornarPaginaVazia() {
            Pageable pageable = PageRequest.of(0, 10);
            Page<FamiliaListResponseDto> emptyPage = Page.empty(pageable);

            Mockito.when(familiaRepository.findAllCustomized(pageable))
                    .thenReturn(emptyPage);

            Page<FamiliaListResponseDto> resultado = familiaService.listar(pageable);

            Assertions.assertTrue(resultado.isEmpty());
        }
    }

    @Nested
    @DisplayName("Deve buscar as famílias corretamente")
    class listarPorId {

        @Test
        @DisplayName("Deve retornar uma família que existe através do seu ID")
        void retornarFamiliaPorId() {
            Familia familia = new Familia();
            familia.setId(1);
            Arquivo foto = new Arquivo();
            foto.setId(10L);
            familia.setFoto(foto);

            Mockito.when(familiaRepository.findById(1))
                    .thenReturn(Optional.of(familia));

            Familia resultado = familiaService.listarPorId(1);

            Assertions.assertEquals(1, resultado.getId());
            Assertions.assertEquals(10L, resultado.getFoto().getId());
        }

        @Test
        @DisplayName("Deve lançar EntidadeNaoEncontradaException quando família não for encontrada pelo ID")
        void retornarExceptionFamiliaNaoEncontrada() {
            Integer id = 99;

            Mockito.when(familiaRepository.findById(id))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> familiaService.listarPorId(id)
            );
        }
    }

    @Nested
    @DisplayName("Deve listar os integrantes da família corretamente")
    class listarIntegrantes {

        @Test
        @DisplayName("Deve retornar responsável e dependentes de uma família existente")
        void retornarIntegrantesDaFamilia() {
            Pessoa responsavel = new Pessoa();
            responsavel.setId(1);
            responsavel.setResponsavel(true);

            Mockito.when(familiaRepository.findByIdFamilia(1))
                    .thenReturn(Optional.of(List.of(responsavel)));

            List<Pessoa> resultado = familiaService.listarIntegrantes(1);

            Assertions.assertEquals(1, resultado.size());
            Assertions.assertTrue(resultado.get(0).getResponsavel());
        }

        @Test
        @DisplayName("Deve lançar EntidadeNaoEncontradaException quando família não for encontrada pelo ID")
        void retornarExceptionFamiliaNaoEncontrada() {
            Integer id = 99;

            Mockito.when(familiaRepository.findByIdFamilia(id))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> familiaService.listarIntegrantes(id)
            );
        }
    }

    @Nested
    @DisplayName("Deve atualizar a família corretamente")
    class atualizar {

        @Test
        @DisplayName("Deve atualizar família, responsável e dependentes com sucesso")
        void atualizarFamilia() {
            Integer id = 1;

            Endereco endereco = new Endereco();
            endereco.setId(1);

            Familia familiaExistente = new Familia();
            familiaExistente.setId(id);
            familiaExistente.setEndereco(endereco);

            Pessoa responsavelExistente = new Pessoa();
            responsavelExistente.setId(10);
            responsavelExistente.setResponsavel(true);

            Pessoa dependenteExistente = new Pessoa();
            dependenteExistente.setId(20);
            dependenteExistente.setResponsavel(false);

            Mockito.when(familiaRepository.findById(id))
                    .thenReturn(Optional.of(familiaExistente));

            Mockito.when(enderecoService.atualizar(Mockito.eq(1), Mockito.any(Endereco.class)))
                    .thenReturn(endereco);

            Mockito.when(familiaRepository.save(Mockito.any(Familia.class)))
                    .thenReturn(familiaExistente);

            Mockito.when(familiaRepository.findByIdFamilia(id))
                    .thenReturn(Optional.of(List.of(responsavelExistente, dependenteExistente)));

            Mockito.when(pessoaService.atualizar(Mockito.eq(10), Mockito.any(Pessoa.class)))
                    .thenReturn(responsavelExistente);

            Mockito.when(pessoaService.salvar(Mockito.any(Pessoa.class)))
                    .thenAnswer(invocation -> invocation.getArgument(0));

            Familia resultado = familiaService.atualizar(id, criarFamiliaRequestDto());

            Assertions.assertNotNull(resultado);
            Mockito.verify(pessoaRepository).deleteAll(List.of(dependenteExistente));
        }

        @Test
        @DisplayName("Deve lançar EntidadeNaoEncontradaException quando família não for encontrada")
        void atualizarFamiliaNaoEncontrada() {
            Integer id = 99;

            Mockito.when(familiaRepository.findById(id))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> familiaService.atualizar(id, criarFamiliaRequestDto())
            );
        }
    }

    @Nested
    @DisplayName("Deve deletar a família corretamente")
    class deletar {

        @Test
        @DisplayName("Deve deletar família, endereço e integrantes com sucesso")
        void deletarFamilia() {
            Integer id = 1;

            Endereco endereco = new Endereco();
            endereco.setId(1);

            Familia familia = new Familia();
            familia.setId(id);
            familia.setEndereco(endereco);

            Pessoa responsavel = new Pessoa();
            responsavel.setId(10);
            responsavel.setResponsavel(true);

            Mockito.when(familiaRepository.findById(id))
                    .thenReturn(Optional.of(familia));

            Mockito.when(familiaRepository.findByIdFamilia(id))
                    .thenReturn(Optional.of(List.of(responsavel)));

            familiaService.deletar(id);

            Mockito.verify(pessoaRepository).deleteAll(List.of(responsavel));
            Mockito.verify(familiaRepository).delete(familia);
            Mockito.verify(enderecoRepository).delete(endereco);
        }

        @Test
        @DisplayName("Deve lançar EntidadeNaoEncontradaException quando família não for encontrada")
        void deletarFamiliaNaoEncontrada() {
            Integer id = 99;

            Mockito.when(familiaRepository.findById(id))
                    .thenReturn(Optional.empty());

            Assertions.assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> familiaService.deletar(id)
            );
        }
    }
}

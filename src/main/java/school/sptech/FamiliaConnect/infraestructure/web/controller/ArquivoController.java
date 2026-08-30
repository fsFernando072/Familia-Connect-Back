package school.sptech.FamiliaConnect.infraestructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.FamiliaConnect.domain.entity.Arquivo;
import school.sptech.FamiliaConnect.application.ports.in.ArquivoUseCase;

@Tag(name = "Arquivos", description = "Operações relacionadas aos arquivos de fotos")
@RestController
@RequestMapping("/arquivos")
public class ArquivoController {

    private final ArquivoUseCase arquivoUseCase;

    public ArquivoController(ArquivoUseCase arquivoUseCase) {
        this.arquivoUseCase = arquivoUseCase;
    }

    @Operation(
            summary = "Visualizar um arquivo",
            description = "Visualiza um arquivo pelo id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arquivo retornado com sucesso"),
    })
    @GetMapping("/{id}/visualizar")
    @PreAuthorize("hasAuthority('visualizar_arquivos')")
    public ResponseEntity<byte[]> visualizar(@PathVariable Integer id) {
        Arquivo entidade = arquivoUseCase.buscarPorId(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(entidade.getMimeType()));
        headers.setContentDisposition(ContentDisposition.inline()
                .filename(entidade.getNomeOriginal())
                .build());
        return ResponseEntity.ok().headers(headers).body(entidade.getDados());
    }
}


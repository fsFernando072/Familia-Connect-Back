package school.sptech.FamiliaConnect.controller;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.FamiliaConnect.model.Arquivo;
import school.sptech.FamiliaConnect.service.ArquivoService;

@RestController
@RequestMapping("/arquivos")
public class ArquivoController {

    private final ArquivoService arquivoService;

    public ArquivoController(ArquivoService arquivoService) {
        this.arquivoService = arquivoService;
    }

    @GetMapping("/{id}/visualizar")
    public ResponseEntity<byte[]> visualizar(@PathVariable Integer id) {
        Arquivo entidade = arquivoService.buscarPorId(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(entidade.getMimeType()));
        headers.setContentDisposition(ContentDisposition.inline()
                .filename(entidade.getNomeOriginal())
                .build());
        return ResponseEntity.ok().headers(headers).body(entidade.getDados());
    }
}


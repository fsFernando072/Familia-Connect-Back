package school.sptech.FamiliaConnect.infraestructure.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import school.sptech.FamiliaConnect.infraestructure.web.dto.cep.ViaCepResponseDto;

@FeignClient(
        name = "viacep-service",
        url = "${url-viacep-service}"
)
@Component
public interface ViaCepClient {

    @GetMapping("/ws/{cep}/json/")
    ViaCepResponseDto buscarPorCep(@PathVariable("cep") String cep);

}

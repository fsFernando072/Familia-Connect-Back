package school.sptech.FamiliaConnect.enums;

import school.sptech.FamiliaConnect.exception.TipoDeArquivoIncompativelException;

import java.util.Arrays;

public enum TipoArquivoEnum {
    PNG("image/png"),
    JPG("image/jpg"),
    JPEG("image/jpeg");

    public String valor;

    TipoArquivoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static void validateEnum(String valor) {
         Boolean isValid = Arrays.stream(TipoArquivoEnum.values())
                .anyMatch(e -> e.getValor().equals(valor.trim()));

         if(!isValid) throw new TipoDeArquivoIncompativelException("Tipo de arquivo não compatível: "+ valor);
    }
}

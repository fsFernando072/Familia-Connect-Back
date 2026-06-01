package school.sptech.FamiliaConnect.dto.familia;

//Query com aliases spring n entende, ele retorna um array/matriz.
//Com isso ele faz um proxy pra pegar os campos com base no nome, seguindo o camelCase
public interface FamiliaListResponseDto {

    Integer getIdFamilia();
    String getNomeResponsavel();
    String getNomeFamilia();
    String getTelefoneResponsavel();
}

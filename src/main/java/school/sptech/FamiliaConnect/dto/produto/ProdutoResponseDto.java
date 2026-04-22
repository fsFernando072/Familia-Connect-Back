package school.sptech.FamiliaConnect.dto.produto;

public class ProdutoResponseDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private Integer id;
    private String nome;
    private Integer quantidade;
    private String descricao;
    private ProdutoCategoria produtoCategoria;

    // Inner Class -----------------------------------------------------------------------------------------------------

    public static class ProdutoCategoria{

        private Integer id;
        private String nome;

        public ProdutoCategoria(Integer id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }

    // Construtores ----------------------------------------------------------------------------------------------------

    public ProdutoResponseDto(Integer id, String nome, Integer quantidade, String descricao, ProdutoCategoria produtoCategoria) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.produtoCategoria = produtoCategoria;
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ProdutoCategoria getProdutoCategoria() {
        return produtoCategoria;
    }

    public void setProdutoCategoria(ProdutoCategoria produtoCategoria) {
        this.produtoCategoria = produtoCategoria;
    }
}

package school.sptech.FamiliaConnect.dto.historicoEstoque;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class HistoricoEstoqueResponseDto {
    private Integer id;
    private Double quantidade;
    private ProdutoHistoricoResponseDto produto;
    private LocalDate dataEstoque;

    public class ProdutoHistoricoResponseDto {
        private Integer id;
        private String nome;
        private String descricao;
        private CategoriaProdutoResponseDto categoria;

        public class CategoriaProdutoResponseDto {
            private Integer id;
            private String nome;

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

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public CategoriaProdutoResponseDto getCategoria() {
            return categoria;
        }

        public void setCategoria(CategoriaProdutoResponseDto categoria) {
            this.categoria = categoria;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public ProdutoHistoricoResponseDto getProduto() {
        return produto;
    }

    public void setProduto(ProdutoHistoricoResponseDto produto) {
        this.produto = produto;
    }

    public LocalDate getDataEstoque() {
        return dataEstoque;
    }

    public void setDataEstoque(LocalDate dataEstoque) {
        this.dataEstoque = dataEstoque;
    }
}

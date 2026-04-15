package school.sptech.FamiliaConnect.dto.familia;

import java.time.LocalDate;

public class FamiliaResponseDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private Integer id;
    private LocalDate dataCadastro;
    private String fotoFamilia;
    private FamiliaEndereco familiaEndereco;

    // Inner Class -----------------------------------------------------------------------------------------------------

    public static class FamiliaEndereco{

        private Integer id;
        private String cep;
        private String bairro;
        private String logradouro;
        private Integer numero;
        private String complemento;
        private String cidade;
        private EnderecoEstado enderecoEstado;

        // Inner Class -----------------

        public static class EnderecoEstado{

            private Integer id;
            private String nome;
            private String sigla;

            public String getNome() {
                return nome;
            }

            public void setNome(String nome) {
                this.nome = nome;
            }

            public String getSigla() {
                return sigla;
            }

            public void setSigla(String sigla) {
                this.sigla = sigla;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }
        }

        public String getCep() {
            return cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public String getLogradouro() {
            return logradouro;
        }

        public void setLogradouro(String logradouro) {
            this.logradouro = logradouro;
        }

        public String getComplemento() {
            return complemento;
        }

        public void setComplemento(String complemento) {
            this.complemento = complemento;
        }

        public EnderecoEstado getEnderecoEstado() {
            return enderecoEstado;
        }

        public void setEnderecoEstado(EnderecoEstado enderecoEstado) {
            this.enderecoEstado = enderecoEstado;
        }

        public Integer getNumero() {
            return numero;
        }

        public void setNumero(Integer numero) {
            this.numero = numero;
        }

        public String getCidade() {
            return cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    // Getters e Setters -----------------------------------------------------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getFotoFamilia() {
        return fotoFamilia;
    }

    public void setFotoFamilia(String fotoFamilia) {
        this.fotoFamilia = fotoFamilia;
    }

    public FamiliaEndereco getFamiliaEndereco() {
        return familiaEndereco;
    }

    public void setFamiliaEndereco(FamiliaEndereco familiaEndereco) {
        this.familiaEndereco = familiaEndereco;
    }
}

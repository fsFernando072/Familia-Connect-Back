package school.sptech.FamiliaConnect.dto.familia;

import java.time.LocalDate;

public class FamiliaResponseDto {

    // Variáveis de instância ------------------------------------------------------------------------------------------

    private Integer id;
    private LocalDate dataCadastro;
    private String fotoFamilia;
    private FamiliaEndereco familiaEndereco;

    // Construtores ----------------------------------------------------------------------------------------------------

    public FamiliaResponseDto() {
    }

    public FamiliaResponseDto(Integer id, LocalDate dataCadastro, String fotoFamilia) {
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.fotoFamilia = fotoFamilia;
    }

    public FamiliaResponseDto(Integer id, LocalDate dataCadastro, String fotoFamilia, FamiliaEndereco familiaEndereco) {
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.fotoFamilia = fotoFamilia;
        this.familiaEndereco = familiaEndereco;
    }

    // Inner Class -----------------------------------------------------------------------------------------------------

    public static class FamiliaEndereco{

        private String cep;
        private String bairro;
        private String logradouro;
        private Integer numero;
        private String complemento;
        private EnderecoEstado enderecoEstado;

        public FamiliaEndereco(String cep, String bairro, String logradouro, Integer numero, String complemento, EnderecoEstado enderecoEstado) {
            this.cep = cep;
            this.bairro = bairro;
            this.logradouro = logradouro;
            this.numero = numero;
            this.complemento = complemento;
            this.enderecoEstado = enderecoEstado;
        }

        // Inner Class -----------------

        public static class EnderecoEstado{

            private String nome;

            public EnderecoEstado(String nome) {
                this.nome = nome;
            }

            public String getNome() {
                return nome;
            }

            public void setNome(String nome) {
                this.nome = nome;
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

package school.sptech.FamiliaConnect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeOriginal;
    private String nomeGerado;
    private String mimeType;
    private Long tamanho;

    @Lob
    private byte[] dados;

    @UpdateTimestamp
    private LocalDateTime dataUpload;

    @ManyToOne
    private CategoriaArquivo categoriaArquivo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeOriginal() {
        return nomeOriginal;
    }

    public void setNomeOriginal(String nomeOriginal) {
        this.nomeOriginal = nomeOriginal;
    }

    public String getNomeGerado() {
        return nomeGerado;
    }

    public void setNomeGerado(String nomeGerado) {
        this.nomeGerado = nomeGerado;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public byte[] getDados() {
        return dados;
    }

    public void setDados(byte[] dados) {
        this.dados = dados;
    }

    public LocalDateTime getDataUpload() {
        return dataUpload;
    }

    public void setDataUpload(LocalDateTime dataUpload) {
        this.dataUpload = dataUpload;
    }

    public CategoriaArquivo getCategoriaArquivo() {
        return categoriaArquivo;
    }

    public void setCategoriaArquivo(CategoriaArquivo categoriaArquivo) {
        this.categoriaArquivo = categoriaArquivo;
    }
}

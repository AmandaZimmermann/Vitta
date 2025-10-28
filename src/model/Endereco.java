package model;

public class Endereco {
    private Integer id;
    private String estado; // CHAR(2)
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;       // nullable
    private String pontoReferencia;   // ponto_referencia - nullable

    // Construtores
    public Endereco() {}

    public Endereco(Integer id, String estado, String cidade, String bairro, String rua,
                    String numero, String complemento, String pontoReferencia) {
        this.id = id;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.pontoReferencia = pontoReferencia;
    }

    public Endereco(String estado, String cidade, String bairro, String rua,
                    String numero, String complemento, String pontoReferencia) {
        this(null, estado, cidade, bairro, rua, numero, complemento, pontoReferencia);
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getPontoReferencia() { return pontoReferencia; }
    public void setPontoReferencia(String pontoReferencia) { this.pontoReferencia = pontoReferencia; }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", estado='" + estado + '\'' +
                ", cidade='" + cidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", pontoReferencia='" + pontoReferencia + '\'' +
                '}';
    }
}
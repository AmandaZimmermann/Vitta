package model;

import java.time.LocalDate; // REVER ESSE LOCAL DATE, TALVEZ REMOVER

public class Pessoa {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private String sexo; // 'M', 'F', 'O'
    private String documento;
    private Integer idEndereco;
    private String telefone;
    private String email;
    private String etnia;
    private String nacionalidade;

    public Pessoa() {}

    public Pessoa(Integer id, String nome, LocalDate dataNascimento, String sexo, String documento,
                  Integer idEndereco, String telefone, String email, String etnia, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.documento = documento;
        this.idEndereco = idEndereco;
        this.telefone = telefone;
        this.email = email;
        this.etnia = etnia;
        this.nacionalidade = nacionalidade;
    }

    public Pessoa(String nome, LocalDate dataNascimento, String sexo, String documento,
                  Integer idEndereco, String telefone, String email, String etnia, String nacionalidade) {
        this(null, nome, dataNascimento, sexo, documento, idEndereco, telefone, email, etnia, nacionalidade);
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public Integer getIdEndereco() { return idEndereco; }
    public void setIdEndereco(Integer idEndereco) { this.idEndereco = idEndereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEtnia() { return etnia; }
    public void setEtnia(String etnia) { this.etnia = etnia; }

    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexo='" + sexo + '\'' +
                ", documento='" + documento + '\'' +
                ", idEndereco=" + idEndereco +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", etnia='" + etnia + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                '}';
    }
}

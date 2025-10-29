package model;

public class Medicamento {
    private Integer id;
    private String nome;
    private String tarja; // 'Branca', 'Amarela', 'Vermelha', 'Preta'

    public Medicamento() {}

    public Medicamento(Integer id, String nome, String tarja) {
        this.id = id;
        this.nome = nome;
        this.tarja = tarja;
    }

    public Medicamento(String nome, String tarja) {
        this(null, nome, tarja);
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTarja() { return tarja; }
    public void setTarja(String tarja) { this.tarja = tarja; }

    @Override
    public String toString() {
        return "Medicamento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tarja='" + tarja + '\'' +
                '}';
    }
}


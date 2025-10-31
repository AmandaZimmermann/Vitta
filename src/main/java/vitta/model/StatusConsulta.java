package vitta.model;

public class StatusConsulta {
    private Integer id;
    private String descricao;

    public StatusConsulta() {}

    public StatusConsulta(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public StatusConsulta(String descricao) {
        this(null, descricao);
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return "StatusConsulta{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}

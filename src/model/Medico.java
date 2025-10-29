package model;

public class Medico {
    private Integer id;
    private Integer idPessoa;
    private String crm;
    private String especialidade;

    public Medico() {}

    public Medico(Integer id, Integer idPessoa, String crm, String especialidade) {
        this.id = id;
        this.idPessoa = idPessoa;
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Medico(Integer idPessoa, String crm, String especialidade) {
        this(null, idPessoa, crm, especialidade);
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdPessoa() { return idPessoa; }
    public void setIdPessoa(Integer idPessoa) { this.idPessoa = idPessoa; }

    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    @Override
    public String toString() {
        return "Medico{" +
                "id=" + id +
                ", idPessoa=" + idPessoa +
                ", crm='" + crm + '\'' +
                ", especialidade='" + especialidade + '\'' +
                '}';
    }
}


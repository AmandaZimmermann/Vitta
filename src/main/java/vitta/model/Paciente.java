package vitta.model;

public class Paciente {
    private Integer id;
    private Integer idPessoa;
    private String tipoSanguineo;
    private String convenio;
    private String numeroCarteirinha;

    public Paciente() {}

    public Paciente(Integer id, Integer idPessoa, String tipoSanguineo, String convenio, String numeroCarteirinha) {
        this.id = id;
        this.idPessoa = idPessoa;
        this.tipoSanguineo = tipoSanguineo;
        this.convenio = convenio;
        this.numeroCarteirinha = numeroCarteirinha;
    }

    public Paciente(Integer idPessoa, String tipoSanguineo, String convenio, String numeroCarteirinha) {
        this(null, idPessoa, tipoSanguineo, convenio, numeroCarteirinha);
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdPessoa() { return idPessoa; }
    public void setIdPessoa(Integer idPessoa) { this.idPessoa = idPessoa; }

    public String getTipoSanguineo() { return tipoSanguineo; }
    public void setTipoSanguineo(String tipoSanguineo) { this.tipoSanguineo = tipoSanguineo; }

    public String getConvenio() { return convenio; }
    public void setConvenio(String convenio) { this.convenio = convenio; }

    public String getNumeroCarteirinha() { return numeroCarteirinha; }
    public void setNumeroCarteirinha(String numeroCarteirinha) { this.numeroCarteirinha = numeroCarteirinha; }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", idPessoa=" + idPessoa +
                ", tipoSanguineo='" + tipoSanguineo + '\'' +
                ", convenio='" + convenio + '\'' +
                ", numeroCarteirinha='" + numeroCarteirinha + '\'' +
                '}';
    }
}

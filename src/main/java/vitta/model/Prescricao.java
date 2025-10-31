package vitta.model;

public class Prescricao {
    private Integer idConsulta;
    private Integer idMedicamento;
    private Integer quantidade;
    private String orientacoes;

    public Prescricao() {}

    public Prescricao(Integer idConsulta, Integer idMedicamento, Integer quantidade, String orientacoes) {
        this.idConsulta = idConsulta;
        this.idMedicamento = idMedicamento;
        this.quantidade = quantidade;
        this.orientacoes = orientacoes;
    }

    // Getters e Setters
    public Integer getIdConsulta() { return idConsulta; }
    public void setIdConsulta(Integer idConsulta) { this.idConsulta = idConsulta; }

    public Integer getIdMedicamento() { return idMedicamento; }
    public void setIdMedicamento(Integer idMedicamento) { this.idMedicamento = idMedicamento; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public String getOrientacoes() { return orientacoes; }
    public void setOrientacoes(String orientacoes) { this.orientacoes = orientacoes; }

    @Override
    public String toString() {
        return "Prescricao{" +
                "idConsulta=" + idConsulta +
                ", idMedicamento=" + idMedicamento +
                ", quantidade=" + quantidade +
                ", orientacoes='" + orientacoes + '\'' +
                '}';
    }
}


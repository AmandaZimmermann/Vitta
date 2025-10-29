package model;

import java.time.LocalDateTime;

public class Consulta {
    private Integer id;
    private Integer idMedico;
    private Integer idPaciente;
    private LocalDateTime dataConsulta;
    private Integer duracaoMinutos;
    private String descricao;
    private Integer idStatus;

    public Consulta() {}

    public Consulta(Integer id, Integer idMedico, Integer idPaciente, LocalDateTime dataConsulta,
                    Integer duracaoMinutos, String descricao, Integer idStatus) {
        this.id = id;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.dataConsulta = dataConsulta;
        this.duracaoMinutos = duracaoMinutos;
        this.descricao = descricao;
        this.idStatus = idStatus;
    }

    public Consulta(Integer idMedico, Integer idPaciente, LocalDateTime dataConsulta,
                    Integer duracaoMinutos, String descricao, Integer idStatus) {
        this(null, idMedico, idPaciente, dataConsulta, duracaoMinutos, descricao, idStatus);
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdMedico() { return idMedico; }
    public void setIdMedico(Integer idMedico) { this.idMedico = idMedico; }

    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }

    public LocalDateTime getDataConsulta() { return dataConsulta; }
    public void setDataConsulta(LocalDateTime dataConsulta) { this.dataConsulta = dataConsulta; }

    public Integer getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(Integer duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getIdStatus() { return idStatus; }
    public void setIdStatus(Integer idStatus) { this.idStatus = idStatus; }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", idMedico=" + idMedico +
                ", idPaciente=" + idPaciente +
                ", dataConsulta=" + dataConsulta +
                ", duracaoMinutos=" + duracaoMinutos +
                ", descricao='" + descricao + '\'' +
                ", idStatus=" + idStatus +
                '}';
    }
}


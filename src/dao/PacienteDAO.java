package dao;

import model.Paciente;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    // CRIAR
    public Paciente criarPaciente(Paciente paciente) throws Exception {
        String sql = """
            INSERT INTO paciente (id_pessoa, tipo_sanguineo, convenio, numero_carteirinha)
            VALUES (?, ?, ?, ?)
            RETURNING id
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, paciente.getIdPessoa());
            ps.setString(2, paciente.getTipoSanguineo());
            ps.setString(3, paciente.getConvenio());
            ps.setString(4, paciente.getNumeroCarteirinha());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                paciente.setId(rs.getInt("id"));
                return paciente;
            } else {
                throw new SQLException("Erro ao inserir paciente.");
            }
        }
    }

    // BUSCAR POR ID
    public Paciente buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM paciente WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearPaciente(rs);
            } else {
                return null;
            }
        }
    }

    // LISTAR TODOS
    public List<Paciente> listarTodos() throws Exception {
        String sql = "SELECT * FROM paciente ORDER BY id";
        List<Paciente> lista = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearPaciente(rs));
            }
        }
        return lista;
    }

    // ATUALIZAR
    public boolean atualizarPaciente(Paciente paciente) throws Exception {
        if (paciente.getId() == null)
            throw new IllegalArgumentException("ID do paciente é obrigatório para atualização.");

        String sql = """
            UPDATE paciente
            SET id_pessoa = ?, tipo_sanguineo = ?, convenio = ?, numero_carteirinha = ?
            WHERE id = ?
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, paciente.getIdPessoa());
            ps.setString(2, paciente.getTipoSanguineo());
            ps.setString(3, paciente.getConvenio());
            ps.setString(4, paciente.getNumeroCarteirinha());
            ps.setInt(5, paciente.getId());

            return ps.executeUpdate() > 0;
        }
    }

    // DELETAR
    public boolean deletarPaciente(int id) throws Exception {
        String sql = "DELETE FROM paciente WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // Mapeamento auxiliar
    private Paciente mapearPaciente(ResultSet rs) throws SQLException {
        Paciente p = new Paciente();
        p.setId(rs.getInt("id"));
        p.setIdPessoa(rs.getInt("id_pessoa"));
        p.setTipoSanguineo(rs.getString("tipo_sanguineo"));
        p.setConvenio(rs.getString("convenio"));
        p.setNumeroCarteirinha(rs.getString("numero_carteirinha"));
        return p;
    }
}

package dao;

import model.StatusConsulta;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusConsultaDAO {

    // CRIAR
    public StatusConsulta criarStatus(StatusConsulta status) throws Exception {
        String sql = "INSERT INTO status_consulta (descricao) VALUES (?) RETURNING id";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status.getDescricao());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                status.setId(rs.getInt("id"));
                return status;
            } else {
                throw new SQLException("Erro ao inserir status de consulta.");
            }
        }
    }

    // BUSCAR POR ID
    public StatusConsulta buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM status_consulta WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearStatus(rs);
            } else {
                return null;
            }
        }
    }

    // LISTAR TODOS
    public List<StatusConsulta> listarTodos() throws Exception {
        String sql = "SELECT * FROM status_consulta ORDER BY id";
        List<StatusConsulta> lista = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearStatus(rs));
            }
        }
        return lista;
    }

    // ATUALIZAR
    public boolean atualizarStatus(StatusConsulta status) throws Exception {
        if (status.getId() == null)
            throw new IllegalArgumentException("ID do status é obrigatório para atualização.");

        String sql = "UPDATE status_consulta SET descricao = ? WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status.getDescricao());
            ps.setInt(2, status.getId());
            return ps.executeUpdate() > 0;
        }
    }

    // DELETAR
    public boolean deletarStatus(int id) throws Exception {
        String sql = "DELETE FROM status_consulta WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // Mapeamento auxiliar
    private StatusConsulta mapearStatus(ResultSet rs) throws SQLException {
        StatusConsulta s = new StatusConsulta();
        s.setId(rs.getInt("id"));
        s.setDescricao(rs.getString("descricao"));
        return s;
    }
}

package dao;

import model.Medicamento;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {

    // CRIAR
    public Medicamento criarMedicamento(Medicamento medicamento) throws Exception {
        String sql = """
            INSERT INTO medicamento (nome, tarja)
            VALUES (?, ?)
            RETURNING id
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, medicamento.getNome());
            ps.setString(2, medicamento.getTarja());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                medicamento.setId(rs.getInt("id"));
                return medicamento;
            } else {
                throw new SQLException("Erro ao inserir medicamento.");
            }
        }
    }

    // BUSCAR POR ID
    public Medicamento buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM medicamento WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapearMedicamento(rs);
            } else {
                return null;
            }
        }
    }

    // LISTAR TODOS
    public List<Medicamento> listarTodos() throws Exception {
        String sql = "SELECT * FROM medicamento ORDER BY nome";
        List<Medicamento> lista = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearMedicamento(rs));
            }
        }
        return lista;
    }

    // ATUALIZAR
    public boolean atualizarMedicamento(Medicamento medicamento) throws Exception {
        if (medicamento.getId() == null)
            throw new IllegalArgumentException("ID do medicamento é obrigatório para atualização.");

        String sql = "UPDATE medicamento SET nome = ?, tarja = ? WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, medicamento.getNome());
            ps.setString(2, medicamento.getTarja());
            ps.setInt(3, medicamento.getId());

            return ps.executeUpdate() > 0;
        }
    }

    // DELETAR
    public boolean deletarMedicamento(int id) throws Exception {
        String sql = "DELETE FROM medicamento WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // Mapeamento auxiliar
    private Medicamento mapearMedicamento(ResultSet rs) throws SQLException {
        Medicamento m = new Medicamento();
        m.setId(rs.getInt("id"));
        m.setNome(rs.getString("nome"));
        m.setTarja(rs.getString("tarja"));
        return m;
    }
}

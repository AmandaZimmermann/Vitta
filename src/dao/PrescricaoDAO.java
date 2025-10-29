package dao;

import model.Prescricao;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescricaoDAO {

    // CRIAR
    public boolean criarPrescricao(Prescricao prescricao) throws Exception {
        String sql = """
            INSERT INTO prescricao (id_consulta, id_medicamento, quantidade, orientacoes)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, prescricao.getIdConsulta());
            ps.setInt(2, prescricao.getIdMedicamento());
            ps.setInt(3, prescricao.getQuantidade());
            ps.setString(4, prescricao.getOrientacoes());

            return ps.executeUpdate() > 0;
        }
    }

    // BUSCAR POR ID (consulta + medicamento)
    public Prescricao buscarPorId(int idConsulta, int idMedicamento) throws Exception {
        String sql = "SELECT * FROM prescricao WHERE id_consulta = ? AND id_medicamento = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idConsulta);
            ps.setInt(2, idMedicamento);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapearPrescricao(rs);
            } else {
                return null;
            }
        }
    }

    // LISTAR TODAS
    public List<Prescricao> listarTodas() throws Exception {
        String sql = "SELECT * FROM prescricao ORDER BY id_consulta, id_medicamento";
        List<Prescricao> lista = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearPrescricao(rs));
            }
        }
        return lista;
    }

    // ATUALIZAR
    public boolean atualizarPrescricao(Prescricao prescricao) throws Exception {
        String sql = """
            UPDATE prescricao
            SET quantidade = ?, orientacoes = ?
            WHERE id_consulta = ? AND id_medicamento = ?
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, prescricao.getQuantidade());
            ps.setString(2, prescricao.getOrientacoes());
            ps.setInt(3, prescricao.getIdConsulta());
            ps.setInt(4, prescricao.getIdMedicamento());

            return ps.executeUpdate() > 0;
        }
    }

    // DELETAR
    public boolean deletarPrescricao(int idConsulta, int idMedicamento) throws Exception {
        String sql = "DELETE FROM prescricao WHERE id_consulta = ? AND id_medicamento = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idConsulta);
            ps.setInt(2, idMedicamento);

            return ps.executeUpdate() > 0;
        }
    }

    // Mapeamento auxiliar
    private Prescricao mapearPrescricao(ResultSet rs) throws SQLException {
        Prescricao p = new Prescricao();
        p.setIdConsulta(rs.getInt("id_consulta"));
        p.setIdMedicamento(rs.getInt("id_medicamento"));
        p.setQuantidade(rs.getInt("quantidade"));
        p.setOrientacoes(rs.getString("orientacoes"));
        return p;
    }
}


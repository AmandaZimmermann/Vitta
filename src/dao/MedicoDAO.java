package dao;

import model.Medico;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    // CRIAR
    public Medico criarMedico(Medico medico) throws Exception {
        String sql = """
            INSERT INTO medico (id_pessoa, crm, especialidade)
            VALUES (?, ?, ?)
            RETURNING id
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, medico.getIdPessoa());
            ps.setString(2, medico.getCrm());
            ps.setString(3, medico.getEspecialidade());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                medico.setId(rs.getInt("id"));
                return medico;
            } else {
                throw new SQLException("Erro ao inserir médico.");
            }
        }
    }

    // BUSCAR POR ID
    public Medico buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM medico WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearMedico(rs);
            } else {
                return null;
            }
        }
    }

    // LISTAR TODOS
    public List<Medico> listarTodos() throws Exception {
        String sql = "SELECT * FROM medico ORDER BY id";
        List<Medico> lista = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearMedico(rs));
            }
        }
        return lista;
    }

    // ATUALIZAR
    public boolean atualizarMedico(Medico medico) throws Exception {
        if (medico.getId() == null)
            throw new IllegalArgumentException("ID do médico é obrigatório para atualização.");

        String sql = """
            UPDATE medico
            SET id_pessoa = ?, crm = ?, especialidade = ?
            WHERE id = ?
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, medico.getIdPessoa());
            ps.setString(2, medico.getCrm());
            ps.setString(3, medico.getEspecialidade());
            ps.setInt(4, medico.getId());

            return ps.executeUpdate() > 0;
        }
    }

    // DELETAR
    public boolean deletarMedico(int id) throws Exception {
        String sql = "DELETE FROM medico WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // Mapeamento auxiliar
    private Medico mapearMedico(ResultSet rs) throws SQLException {
        Medico m = new Medico();
        m.setId(rs.getInt("id"));
        m.setIdPessoa(rs.getInt("id_pessoa"));
        m.setCrm(rs.getString("crm"));
        m.setEspecialidade(rs.getString("especialidade"));
        return m;
    }
}


package vitta.dao;

import vitta.database.DBUtils;
import vitta.model.Prescricao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescricaoDAO {

    // CRIAR
    public boolean criarPrescricao(Prescricao prescricao) {
        String sql = "INSERT INTO prescricao (id_consulta, id_medicamento, quantidade, orientacoes) "
                + "VALUES (?, ?, ?, ?)";

        try {
            DBUtils.execute(sql,
                    prescricao.getIdConsulta(),
                    prescricao.getIdMedicamento(),
                    prescricao.getQuantidade(),
                    prescricao.getOrientacoes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // BUSCAR POR ID (consulta + medicamento)
    public Prescricao buscarPorId(int idConsulta, int idMedicamento) {
        String sql = "SELECT * FROM prescricao WHERE id_consulta = ? AND id_medicamento = ?";

        try (ResultSet rs = DBUtils.query(sql, idConsulta, idMedicamento)) {
            if (rs.next()) {
                return mapearPrescricao(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // LISTAR TODAS
    public List<Prescricao> listarTodas() {
        List<Prescricao> lista = new ArrayList<>();
        String sql = "SELECT * FROM prescricao ORDER BY id_consulta, id_medicamento";

        try (ResultSet rs = DBUtils.query(sql)) {
            while (rs.next()) {
                lista.add(mapearPrescricao(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ATUALIZAR
    public boolean atualizarPrescricao(Prescricao prescricao) {
        String sql = "UPDATE prescricao "
                + "SET quantidade = ?, orientacoes = ? "
                + "WHERE id_consulta = ? AND id_medicamento = ?";

        try {
            DBUtils.execute(sql,
                    prescricao.getQuantidade(),
                    prescricao.getOrientacoes(),
                    prescricao.getIdConsulta(),
                    prescricao.getIdMedicamento());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETAR
    public boolean deletarPrescricao(int idConsulta, int idMedicamento) {
        String sql = "DELETE FROM prescricao WHERE id_consulta = ? AND id_medicamento = ?";

        try {
            DBUtils.execute(sql, idConsulta, idMedicamento);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

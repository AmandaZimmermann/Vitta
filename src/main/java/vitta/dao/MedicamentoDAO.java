package vitta.dao;

import vitta.database.DBUtils;
import vitta.model.Medicamento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {

    // CRIAR
    public Medicamento criarMedicamento(Medicamento medicamento) {
        String sql  = "INSERT INTO medicamento (nome, tarja) "
                    + "VALUES (?, ?) RETURNING id";

        try (ResultSet rs = DBUtils.query(sql,
                medicamento.getNome(),
                medicamento.getTarja())) {

            if (rs.next()) {
                medicamento.setId(rs.getInt("id"));
                return medicamento;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // BUSCAR POR ID
    public Medicamento buscarPorId(int id) {
        String sql = "SELECT * FROM medicamento WHERE id = ?";

        try (ResultSet rs = DBUtils.query(sql, id)) {
            if (rs.next()) {
                return mapearMedicamento(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LISTAR TODOS
    public List<Medicamento> listarTodos() {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicamento ORDER BY nome";

        try (ResultSet rs = DBUtils.query(sql)) {
            while (rs.next()) {
                lista.add(mapearMedicamento(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ATUALIZAR
    public boolean atualizarMedicamento(Medicamento medicamento) {
        if (medicamento.getId() == null) {
            throw new IllegalArgumentException("ID do medicamento é obrigatório para atualização.");
        }

        String sql = "UPDATE medicamento SET nome = ?, tarja = ? WHERE id = ?";

        try {
            DBUtils.execute(sql,
                    medicamento.getNome(),
                    medicamento.getTarja(),
                    medicamento.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETAR
    public boolean deletarMedicamento(int id) {
        try {
            DBUtils.execute("DELETE FROM medicamento WHERE id = ?", id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

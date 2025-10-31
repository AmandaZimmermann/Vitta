package vitta.dao;

import vitta.database.DBUtils;
import vitta.model.StatusConsulta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusConsultaDAO {

    // CRIAR
    public StatusConsulta criarStatus(StatusConsulta status) {
        String sql = "INSERT INTO status_consulta (descricao) VALUES (?) RETURNING id";

        try (ResultSet rs = DBUtils.query(sql, status.getDescricao())) {
            if (rs.next()) {
                status.setId(rs.getInt("id"));
                return status;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // BUSCAR POR ID
    public StatusConsulta buscarPorId(int id) {
        String sql = "SELECT * FROM status_consulta WHERE id = ?";

        try (ResultSet rs = DBUtils.query(sql, id)) {
            if (rs.next()) {
                return mapearStatus(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // LISTAR TODOS
    public List<StatusConsulta> listarTodos() {
        List<StatusConsulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM status_consulta ORDER BY id";

        try (ResultSet rs = DBUtils.query(sql)) {
            while (rs.next()) {
                lista.add(mapearStatus(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ATUALIZAR
    public boolean atualizarStatus(StatusConsulta status) {
        if (status.getId() == null) {
            throw new IllegalArgumentException("ID do status é obrigatório para atualização.");
        }

        String sql = "UPDATE status_consulta SET descricao = ? WHERE id = ?";

        try {
            DBUtils.execute(sql, status.getDescricao(), status.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETAR
    public boolean deletarStatus(int id) {
        String sql = "DELETE FROM status_consulta WHERE id = ?";

        try {
            DBUtils.execute(sql, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

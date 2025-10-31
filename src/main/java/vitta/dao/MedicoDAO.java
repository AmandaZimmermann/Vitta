package vitta.dao;

import vitta.database.DBUtils;
import vitta.model.Medico;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    // CRIAR
    public Medico criarMedico(Medico medico) {
        String sql  = "INSERT INTO medico (id_pessoa, crm, especialidade) "
                    + "VALUES (?, ?, ?) RETURNING id";

        try (ResultSet rs = DBUtils.query(sql,
                medico.getIdPessoa(),
                medico.getCrm(),
                medico.getEspecialidade())) {

            if (rs.next()) {
                medico.setId(rs.getInt("id"));
                return medico;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // BUSCAR POR ID
    public Medico buscarPorId(int id) {
        String sql = "SELECT * FROM medico WHERE id = ?";

        try (ResultSet rs = DBUtils.query(sql, id)) {
            if (rs.next()) {
                return mapearMedico(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LISTAR TODOS
    public List<Medico> listarTodos() {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM medico ORDER BY id";

        try (ResultSet rs = DBUtils.query(sql)) {
            while (rs.next()) {
                lista.add(mapearMedico(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ATUALIZAR
    public boolean atualizarMedico(Medico medico) {
        if (medico.getId() == null) {
            throw new IllegalArgumentException("ID do médico é obrigatório para atualização.");
        }

        String sql  = "UPDATE medico "
                    + "SET id_pessoa = ?, crm = ?, especialidade = ? "
                    + "WHERE id = ?";

        try {
            DBUtils.execute(sql,
                    medico.getIdPessoa(),
                    medico.getCrm(),
                    medico.getEspecialidade(),
                    medico.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETAR
    public boolean deletarMedico(int id) {
        try {
            DBUtils.execute("DELETE FROM medico WHERE id = ?", id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

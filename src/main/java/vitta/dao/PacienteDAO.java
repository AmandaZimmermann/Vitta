package vitta.dao;

import vitta.database.DBUtils;
import vitta.model.Paciente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    // CRIAR
    public Paciente criarPaciente(Paciente paciente) {
        String sql  = "INSERT INTO paciente (id_pessoa, tipo_sanguineo, convenio, numero_carteirinha) "
                    + "VALUES (?, ?, ?, ?) RETURNING id";

        try (ResultSet rs = DBUtils.query(sql,
                paciente.getIdPessoa(),
                paciente.getTipoSanguineo(),
                paciente.getConvenio(),
                paciente.getNumeroCarteirinha())) {

            if (rs.next()) {
                paciente.setId(rs.getInt("id"));
                return paciente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // BUSCAR POR ID
    public Paciente buscarPorId(int id) {
        String sql = "SELECT * FROM paciente WHERE id = ?";

        try (ResultSet rs = DBUtils.query(sql, id)) {
            if (rs.next()) {
                return mapearPaciente(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LISTAR TODOS
    public List<Paciente> listarTodos() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente ORDER BY id";

        try (ResultSet rs = DBUtils.query(sql)) {
            while (rs.next()) {
                lista.add(mapearPaciente(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ATUALIZAR
    public boolean atualizarPaciente(Paciente paciente) {
        if (paciente.getId() == null) {
            throw new IllegalArgumentException("ID do paciente é obrigatório para atualização.");
        }

        String sql  = "UPDATE paciente "
                    + "SET id_pessoa = ?, tipo_sanguineo = ?, convenio = ?, numero_carteirinha = ? "
                    + "WHERE id = ?";

        try {
            DBUtils.execute(sql,
                    paciente.getIdPessoa(),
                    paciente.getTipoSanguineo(),
                    paciente.getConvenio(),
                    paciente.getNumeroCarteirinha(),
                    paciente.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETAR
    public boolean deletarPaciente(int id) {
        try {
            DBUtils.execute("DELETE FROM paciente WHERE id = ?", id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

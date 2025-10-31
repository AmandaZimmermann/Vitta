package vitta.dao;

import vitta.database.DBUtils;
import vitta.model.Endereco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {

    // CRIAR (INSERT)
    public Endereco criarEndereco(Endereco endereco) {
        String sql  = "INSERT INTO endereco (estado, cidade, bairro, rua, numero, complemento, ponto_referencia)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id ";

        try (ResultSet rs = DBUtils.query(sql,
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getPontoReferencia())) {

            if (rs.next()) {
                endereco.setId(rs.getInt("id"));
                return endereco;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LER por ID
    public Endereco buscarPorId(int id) {
        String sql  = "SELECT id, estado, cidade, bairro, rua, numero, complemento, ponto_referencia "
                    + "FROM endereco WHERE id = ?";

        try (ResultSet rs = DBUtils.query(sql, id)) {
            if (rs.next()) {
                return mapearEndereco(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LISTAR todos
    public List<Endereco> listarTodos() {
        List<Endereco> lista = new ArrayList<>();
        String sql  = "SELECT id, estado, cidade, bairro, rua, numero, complemento, ponto_referencia"
                    + "FROM endereco ORDER BY id";

        try (ResultSet rs = DBUtils.query(sql)) {
            while (rs.next()) {
                lista.add(mapearEndereco(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ATUALIZAR
    public boolean atualizarEndereco(Endereco endereco) {
        if (endereco.getId() == null) {
            throw new IllegalArgumentException("O id do endereço é obrigatório para atualizar.");
        }

        String sql  = "UPDATE endereco"
                    + "SET estado = ?, cidade = ?, bairro = ?, rua = ?, numero = ?, complemento = ?, ponto_referencia = ?"
                    + "WHERE id = ?";

        try {
            DBUtils.execute(sql,
                    endereco.getEstado(),
                    endereco.getCidade(),
                    endereco.getBairro(),
                    endereco.getRua(),
                    endereco.getNumero(),
                    endereco.getComplemento(),
                    endereco.getPontoReferencia(),
                    endereco.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETAR
    public boolean deletarEndereco(int id) {
        try {
            DBUtils.execute("DELETE FROM endereco WHERE id = ?", id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método auxiliar para mapear ResultSet -> Endereco
    private Endereco mapearEndereco(ResultSet rs) throws SQLException {
        Endereco e = new Endereco();
        e.setId(rs.getInt("id"));
        e.setEstado(rs.getString("estado"));
        e.setCidade(rs.getString("cidade"));
        e.setBairro(rs.getString("bairro"));
        e.setRua(rs.getString("rua"));
        e.setNumero(rs.getString("numero"));
        e.setComplemento(rs.getString("complemento"));
        e.setPontoReferencia(rs.getString("ponto_referencia"));
        return e;
    }
}

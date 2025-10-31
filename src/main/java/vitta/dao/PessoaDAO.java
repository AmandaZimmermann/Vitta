package vitta.dao;

import vitta.database.DBUtils;
import vitta.model.Pessoa;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    // CRIAR
    public Pessoa criarPessoa(Pessoa pessoa) {
        String sql  = "INSERT INTO pessoa (nome, data_nascimento, sexo, documento, id_endereco, telefone, email, etnia, nacionalidade) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

        try (ResultSet rs = DBUtils.query(sql,
                pessoa.getNome(),
                Date.valueOf(pessoa.getDataNascimento()),
                pessoa.getSexo(),
                pessoa.getDocumento(),
                pessoa.getIdEndereco(),
                pessoa.getTelefone(),
                pessoa.getEmail(),
                pessoa.getEtnia(),
                pessoa.getNacionalidade())) {

            if (rs.next()) {
                pessoa.setId(rs.getInt("id"));
                return pessoa;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // BUSCAR POR ID
    public Pessoa buscarPorId(int id) {
        String sql = "SELECT * FROM pessoa WHERE id = ?";

        try (ResultSet rs = DBUtils.query(sql, id)) {
            if (rs.next()) {
                return mapearPessoa(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // LISTAR TODOS
    public List<Pessoa> listarTodos() {
        List<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT * FROM pessoa ORDER BY id";

        try (ResultSet rs = DBUtils.query(sql)) {
            while (rs.next()) {
                lista.add(mapearPessoa(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ATUALIZAR
    public boolean atualizarPessoa(Pessoa pessoa) {
        if (pessoa.getId() == null) {
            throw new IllegalArgumentException("ID é obrigatório para atualização.");
        }

        String sql  = "UPDATE pessoa SET nome = ?, data_nascimento = ?, sexo = ?, documento = ?, id_endereco = ?, "
                    + "telefone = ?, email = ?, etnia = ?, nacionalidade = ? WHERE id = ?";

        try {
            DBUtils.execute(sql,
                    pessoa.getNome(),
                    Date.valueOf(pessoa.getDataNascimento()),
                    pessoa.getSexo(),
                    pessoa.getDocumento(),
                    pessoa.getIdEndereco(),
                    pessoa.getTelefone(),
                    pessoa.getEmail(),
                    pessoa.getEtnia(),
                    pessoa.getNacionalidade(),
                    pessoa.getId());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETAR
    public boolean deletarPessoa(int id) {
        try {
            DBUtils.execute("DELETE FROM pessoa WHERE id = ?", id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Mapeamento auxiliar
    private Pessoa mapearPessoa(ResultSet rs) throws SQLException {
        Pessoa p = new Pessoa();
        p.setId(rs.getInt("id"));
        p.setNome(rs.getString("nome"));
        p.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        p.setSexo(rs.getString("sexo"));
        p.setDocumento(rs.getString("documento"));
        p.setIdEndereco(rs.getInt("id_endereco"));
        p.setTelefone(rs.getString("telefone"));
        p.setEmail(rs.getString("email"));
        p.setEtnia(rs.getString("etnia"));
        p.setNacionalidade(rs.getString("nacionalidade"));
        return p;
    }
}

package vitta.dao;

import vitta.database.DBUtils;
import vitta.model.PermissaoTipo;
import vitta.model.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // CRIAR
    public Usuario criarUsuario(Usuario usuario) throws SQLException {
        String sql  = "INSERT INTO usuario (username, senha, permissao, id_pessoa, ativo) "
                    + "VALUES (?, ?, ?, ?, ?) RETURNING id";

        try (ResultSet rs = DBUtils.query(sql,
                usuario.getUsername(),
                usuario.getSenha(),
                usuario.getPermissao().getCodigo(),
                usuario.getIdPessoa(),
                usuario.getAtivo())) {

            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                return usuario;
            } else {
                throw new SQLException("Erro ao inserir usuário.");
            }
        }
    }

    // BUSCAR POR ID
    public Usuario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try (ResultSet rs = DBUtils.query(sql, id)) {
            if (rs.next()) {
                return mapearUsuario(rs);
            } else {
                return null;
            }
        }
    }

    // LISTAR TODOS
    public List<Usuario> listarTodos() throws SQLException {
        String sql = "SELECT * FROM usuario ORDER BY id";
        List<Usuario> lista = new ArrayList<>();

        try (ResultSet rs = DBUtils.query(sql)) {
            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }
        }
        return lista;
    }

    // ATUALIZAR
    public boolean atualizarUsuario(Usuario usuario) throws SQLException {
        if (usuario.getId() == null)
            throw new IllegalArgumentException("ID do usuário é obrigatório para atualização.");

        String sql  = "UPDATE usuario "
                    + "SET username = ?, senha = ?, permissao = ?, id_pessoa = ?, ativo = ? "
                    + "WHERE id = ?";

        DBUtils.execute(sql,
                usuario.getUsername(),
                usuario.getSenha(),
                usuario.getPermissao().getCodigo(),
                usuario.getIdPessoa(),
                usuario.getAtivo(),
                usuario.getId());

        // Retornamos true, pois se não houve exceção, o update ocorreu
        return true;
    }

    // DELETAR
    public boolean deletarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        DBUtils.execute(sql, id);
        return true;
    }

    // LOGIN (opcional)
    public Usuario autenticar(String username, String senha) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE username = ? AND senha = ? AND ativo = TRUE";
        try (ResultSet rs = DBUtils.query(sql, username, senha)) {
            if (rs.next()) {
                return mapearUsuario(rs);
            } else {
                return null;
            }
        }
    }

    // Mapeamento auxiliar
    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setUsername(rs.getString("username"));
        u.setSenha(rs.getString("senha"));
        u.setPermissao(PermissaoTipo.fromString(rs.getString("permissao")));
        u.setIdPessoa(rs.getInt("id_pessoa"));
        u.setAtivo(rs.getBoolean("ativo"));
        return u;
    }
}

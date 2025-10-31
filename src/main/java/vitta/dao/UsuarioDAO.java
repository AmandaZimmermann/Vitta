package vitta.dao;

import util.DatabaseUtil;
import vitta.model.PermissaoTipo;
import vitta.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // CRIAR
    public Usuario criarUsuario(Usuario usuario) throws Exception {
        String sql = """
            INSERT INTO usuario (username, senha, permissao, id_pessoa, ativo)
            VALUES (?, ?, ?, ?, ?)
            RETURNING id
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getPermissao().getCodigo());
            ps.setInt(4, usuario.getIdPessoa());
            ps.setBoolean(5, usuario.getAtivo());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                return usuario;
            } else {
                throw new SQLException("Erro ao inserir usuário.");
            }
        }
    }

    // BUSCAR POR ID
    public Usuario buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearUsuario(rs);
            } else {
                return null;
            }
        }
    }

    // LISTAR TODOS
    public List<Usuario> listarTodos() throws Exception {
        String sql = "SELECT * FROM usuario ORDER BY id";
        List<Usuario> lista = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }
        }
        return lista;
    }

    // ATUALIZAR
    public boolean atualizarUsuario(Usuario usuario) throws Exception {
        if (usuario.getId() == null)
            throw new IllegalArgumentException("ID do usuário é obrigatório para atualização.");

        String sql = """
            UPDATE usuario
            SET username = ?, senha = ?, permissao = ?, id_pessoa = ?, ativo = ?
            WHERE id = ?
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getPermissao().getCodigo());
            ps.setInt(4, usuario.getIdPessoa());
            ps.setBoolean(5, usuario.getAtivo());
            ps.setInt(6, usuario.getId());

            return ps.executeUpdate() > 0;
        }
    }

    // DELETAR
    public boolean deletarUsuario(int id) throws Exception {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // LOGIN (opcional)
    public Usuario autenticar(String username, String senha) throws Exception {
        String sql = "SELECT * FROM usuario WHERE username = ? AND senha = ? AND ativo = TRUE";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();
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


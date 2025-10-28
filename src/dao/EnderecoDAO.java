package dao;

import model.Endereco;
import util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class EnderecoDAO {

    // CRIAR (INSERT)
    public Endereco criarEndereco(Endereco endereco) throws Exception {
        String sql = "INSERT INTO endereco (estado, cidade, bairro, rua, numero, complemento, ponto_referencia) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, endereco.getEstado());
            ps.setString(2, endereco.getCidade());
            ps.setString(3, endereco.getBairro());
            ps.setString(4, endereco.getRua());
            ps.setString(5, endereco.getNumero());
            ps.setString(6, endereco.getComplemento());
            ps.setString(7, endereco.getPontoReferencia());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                endereco.setId(rs.getInt("id"));
                return endereco;
            } else {
                throw new SQLException("Não foi possível inserir o endereço.");
            }
        }
    }

    // LER por id (SELECT)
    public Endereco buscarPorId(int id) throws Exception {
        String sql = "SELECT id, estado, cidade, bairro, rua, numero, complemento, ponto_referencia FROM endereco WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearEndereco(rs);
                } else {
                    return null;
                }
            }
        }
    }

    // LISTAR todos
    public List<Endereco> listarTodos() throws Exception {
        String sql = "SELECT id, estado, cidade, bairro, rua, numero, complemento, ponto_referencia FROM endereco ORDER BY id";
        List<Endereco> lista = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearEndereco(rs));
            }
        }
        return lista;
    }

    // ATUALIZAR
    public boolean atualizarEndereco(Endereco endereco) throws Exception {
        if (endereco.getId() == null) {
            throw new IllegalArgumentException("O id do endereço é obrigatório para atualizar.");
        }
        String sql = "UPDATE endereco SET estado = ?, cidade = ?, bairro = ?, rua = ?, numero = ?, complemento = ?, ponto_referencia = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, endereco.getEstado());
            ps.setString(2, endereco.getCidade());
            ps.setString(3, endereco.getBairro());
            ps.setString(4, endereco.getRua());
            ps.setString(5, endereco.getNumero());
            ps.setString(6, endereco.getComplemento());
            ps.setString(7, endereco.getPontoReferencia());
            ps.setInt(8, endereco.getId());

            int atualizados = ps.executeUpdate();
            return atualizados > 0;
        }
    }

    // DELETAR
    public boolean deletarEndereco(int id) throws Exception {
        String sql = "DELETE FROM endereco WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int removidos = ps.executeUpdate();
            return removidos > 0;
        }
    }

    // método auxiliar para mapear ResultSet -> Endereco
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

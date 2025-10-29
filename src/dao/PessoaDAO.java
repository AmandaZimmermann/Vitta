package dao;

import model.Pessoa;
import util.DatabaseUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    // CRIAR
    public Pessoa criarPessoa(Pessoa pessoa) throws Exception {
        String sql = """
            INSERT INTO pessoa (nome, data_nascimento, sexo, documento, id_endereco, telefone, email, etnia, nacionalidade)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            RETURNING id
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pessoa.getNome());
            ps.setDate(2, Date.valueOf(pessoa.getDataNascimento()));
            ps.setString(3, pessoa.getSexo());
            ps.setString(4, pessoa.getDocumento());
            ps.setInt(5, pessoa.getIdEndereco());
            ps.setString(6, pessoa.getTelefone());
            ps.setString(7, pessoa.getEmail());
            ps.setString(8, pessoa.getEtnia());
            ps.setString(9, pessoa.getNacionalidade());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pessoa.setId(rs.getInt("id"));
                return pessoa;
            } else {
                throw new SQLException("Erro ao inserir pessoa.");
            }
        }
    }

    // BUSCAR POR ID
    public Pessoa buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM pessoa WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapearPessoa(rs);
            } else {
                return null;
            }
        }
    }

    // LISTAR TODOS
    public List<Pessoa> listarTodos() throws Exception {
        String sql = "SELECT * FROM pessoa ORDER BY id";
        List<Pessoa> lista = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearPessoa(rs));
            }
        }
        return lista;
    }

    // ATUALIZAR
    public boolean atualizarPessoa(Pessoa pessoa) throws Exception {
        if (pessoa.getId() == null) throw new IllegalArgumentException("ID é obrigatório para atualização.");

        String sql = """
            UPDATE pessoa SET nome = ?, data_nascimento = ?, sexo = ?, documento = ?, id_endereco = ?,
                              telefone = ?, email = ?, etnia = ?, nacionalidade = ?
            WHERE id = ?
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pessoa.getNome());
            ps.setDate(2, Date.valueOf(pessoa.getDataNascimento()));
            ps.setString(3, pessoa.getSexo());
            ps.setString(4, pessoa.getDocumento());
            ps.setInt(5, pessoa.getIdEndereco());
            ps.setString(6, pessoa.getTelefone());
            ps.setString(7, pessoa.getEmail());
            ps.setString(8, pessoa.getEtnia());
            ps.setString(9, pessoa.getNacionalidade());
            ps.setInt(10, pessoa.getId());

            return ps.executeUpdate() > 0;
        }
    }

    // DELETAR
    public boolean deletarPessoa(int id) throws Exception {
        String sql = "DELETE FROM pessoa WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
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

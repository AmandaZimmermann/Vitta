package vitta.database;

import java.sql.*;

/**
 * Classe utilitária para execução de queries no banco.
 */
public class DBUtils {

    private static final String URL = "jdbc:postgresql://db.nwtukqadogavmfgufyjq.supabase.co:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "database_vitta_admin";

    /**
     * Executa comandos de DML (INSERT, UPDATE, DELETE).
     *
     * @param sql    SQL com placeholders (?) para parâmetros.
     * @param params Valores que substituem os placeholders na query.
     */
    public static void execute(String sql, Object... params) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executa uma query de seleção (SELECT) com parâmetros e retorna um ResultSet.
     * É responsabilidade do chamador fechar o ResultSet e a Connection associada.
     *
     * @param sql    A query SQL com placeholders (?) para parâmetros.
     * @param params Valores que substituem os placeholders na query.
     * @return ResultSet contendo os resultados da query.
     * @throws SQLException Caso ocorra algum erro de acesso ao banco.
     */
    public static ResultSet query(String sql, Object... params) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = conn.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }

        return stmt.executeQuery();
    }
}
package util;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseUtil {
    private static final String PROPS_FILE = "/db.properties";
    private static String url;
    private static String user;
    private static String password;

    static {
        try (InputStream is = DatabaseUtil.class.getResourceAsStream(PROPS_FILE)) {
            Properties props = new Properties();
            props.load(is);
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
            // Se necessário, registre o driver aqui (para alguns bancos/versões)
            // Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Erro ao carregar db.properties: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, user, password);
    }
}
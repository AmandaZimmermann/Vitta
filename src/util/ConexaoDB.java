package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//MODELO BASE PEGO DO PROJETO DA TATIANA

public class ConexaoDB {

    private Connection Conn = null;
    private Statement s = null;

    public ConexaoDB(String database) {
        try {
            Class.forName("org.postgresql.Driver");
            this.Conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+database,"postgres","postgres");
            this.s = this.Conn.createStatement();
            System.out.println("Conectado ao banco de dados: "+database);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return Conn;
    }

    public Statement getS() {
        return s;
    }
}

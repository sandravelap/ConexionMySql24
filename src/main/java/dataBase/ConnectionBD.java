package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
    private static final String URL = "jdbc:mysql://localhost:3307/pruebaIJ";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public Connection conectarDB() {
        Connection miCon = null;
        try {
            miCon = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return miCon;
    }

}

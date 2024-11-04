package dataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionDataBase {
    ConnectionSGBD conexionSGBD = new ConnectionSGBD();
    public void crearDB(String nombre){
        //creamos una conexión al SGBD con un try with resources para asegurar que se cierra
        try (Connection con = conexionSGBD.conectarSGBD()){
            //creamos un objeto Statement para mandar sentencias al SGBD
            Statement stmCreate = con.createStatement();
            //ejecutamos en el SGBD la sentencia
            stmCreate.executeUpdate("CREATE DATABASE "+nombre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void borrarDB(String nombre){
        //creamos una conexión al SGBD con un try with resources para asegurar que se cierra
        try (Connection con = conexionSGBD.conectarSGBD()){
            //creamos un objeto Statement para mandar sentencias al SGBD
            Statement stmCreate = con.createStatement();
            //ejecutamos en el SGBD la sentencia
            stmCreate.executeUpdate("DROP DATABASE IF EXISTS "+nombre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

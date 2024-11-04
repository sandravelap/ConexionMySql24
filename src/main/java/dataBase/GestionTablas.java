package dataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionTablas {
    ConnectionBD conBD = new ConnectionBD();
    public void crearTablas(){
        //creamos una conexión a la BD con un try with resources para asegurar que se cierra
        try (Connection con = conBD.conectarDB()){
            //creamos un objeto Statement para mandar sentencias al SGBD
            Statement stmCreate = con.createStatement();
            //ejecutamos en el SGBD la sentencia
            String createTDepartamentos ="CREATE TABLE departamentos (\n" +
                    " dept_no  TINYINT(2) NOT NULL PRIMARY KEY,\n" +
                    " dnombre  VARCHAR(15), \n" +
                    " loc      VARCHAR(15)\n" +
                    ")";
            String createTEmpleados ="CREATE TABLE empleados (\n" +
                    " emp_no    SMALLINT(4)  NOT NULL PRIMARY KEY,\n" +
                    " apellido  VARCHAR(10),\n" +
                    " oficio    VARCHAR(10),\n" +
                    " dir       SMALLINT,\n" +
                    " fecha_alt DATE      ,\n" +
                    " salario   FLOAT(6,2),\n" +
                    " comision  FLOAT(6,2),\n" +
                    " dept_no   TINYINT(2) NOT NULL, \n" +
                    " FOREIGN KEY(dept_no) REFERENCES departamentos(dept_no)\n" +
                    ")";
            stmCreate.executeUpdate(createTDepartamentos);
            stmCreate.executeUpdate(createTEmpleados);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void borrarTablas(){
        //creamos la conexión
        try(Connection miCon = conBD.conectarDB()){
        //creamos un objeto Statement para mandar sentencias al SGBD
            Statement stmDrop = miCon.createStatement();
            //ejecutamos en el SGBD la sentencia
            stmDrop.executeUpdate("DROP TABLE IF EXISTS empleados;");
            stmDrop.executeUpdate("DROP TABLE IF EXISTS departamentos;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

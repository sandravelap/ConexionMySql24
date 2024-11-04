package repositories;

import dao.Departamento;
import dataBase.ConnectionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//en la arquitectura de capas con patrón DAO (Data Access Object)
//en los repositorios accedemos al información persistida y la almacenamos en memoria
//haciendo uso de las clases de Java que la modelan en este caso de las clases del paquete DAO
public class DepartamentoRepository {

    ConnectionBD conBD = new ConnectionBD();

    public ArrayList<Departamento> listarDepartamentos(){
        //instanciamos los objetos DAO donde almacenar la información que se va a recuperar
        ArrayList<Departamento> listadoDepartamentos = new ArrayList<Departamento>();
        Departamento depAux;
        //conectamos con la base de datos asegurando la desconexión con el try with resources
        try(Connection miCon = conBD.conectarDB()){
            //instanciamos un Statement porque la consulta no tiene parámetros
            Statement listDepsStatement = miCon.createStatement();
            //recuperamos en un ResultSet ejecutando la sentencia con un executeQuery
            //es recomendable comprobar que la sentencia SQL es correcta en la consola de la base de datos
            ResultSet rs = listDepsStatement.executeQuery("select * from departamentos");
            //recorremos los resultados de la consulta almacenando la información recuperada en los DAO
            while(rs.next()){
                depAux = new Departamento();
                depAux.setIdDepartamento(rs.getInt("dept_no"));
                depAux.setNombre(rs.getString("dnombre"));
                depAux.setLocalidad(rs.getString("loc"));
                listadoDepartamentos.add(depAux);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listadoDepartamentos;
    }
}

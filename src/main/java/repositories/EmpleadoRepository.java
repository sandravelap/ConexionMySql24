package repositories;

import dao.Departamento;
import dao.Empleado;
import dataBase.ConnectionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmpleadoRepository {
    ConnectionBD conBD = new ConnectionBD();
    DepartamentoRepository departamentoRepository = new DepartamentoRepository();
    public ArrayList<Empleado> listarEmpleados(){
        //instanciamos los objetos DAO donde almacenar la información que se va a recuperar
        ArrayList<Empleado> listadoEmpleados = new ArrayList<Empleado>();
        Empleado empAux;
        //Integer idDepAux;
        //conectamos con la base de datos asegurando la desconexión con el try with resources
        try(Connection miCon = conBD.conectarDB()){
            //instanciamos un Statement porque la consulta no tiene parámetros
            Statement listDepsStatement = miCon.createStatement();
            //recuperamos en un ResultSet ejecutando la sentencia con un executeQuery
            //es recomendable comprobar que la sentencia SQL es correcta en la consola de la base de datos
            ResultSet rs = listDepsStatement.executeQuery("select * from empleados");
            //recorremos los resultados de la consulta almacenando la información recuperada en los DAO
            while(rs.next()){
                empAux = new Empleado();
                empAux.setIdEmpleado(rs.getInt("emp_no"));
                empAux.setApellido(rs.getString("apellido"));
                empAux.setFechaAlta(rs.getDate("fecha_alt"));
                empAux.setDepartamento(departamentoRepository.depById(rs.getInt("dept_no")));
                //añadimos el objeto creado a la lista de empledos
                listadoEmpleados.add(empAux);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listadoEmpleados;
    }
}

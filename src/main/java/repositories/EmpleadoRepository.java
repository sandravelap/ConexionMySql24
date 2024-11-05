package repositories;

import dao.EmpleadoDAO;
import dataBase.ConnectionBD;

import java.sql.*;
import java.util.ArrayList;

public class EmpleadoRepository {
    ConnectionBD conBD = new ConnectionBD();
    DepartamentoRepository departamentoRepository = new DepartamentoRepository();
    public ArrayList<EmpleadoDAO> listarEmpleados(){
        //instanciamos los objetos DAO donde almacenar la información que se va a recuperar
        ArrayList<EmpleadoDAO> listadoEmpleados = new ArrayList<EmpleadoDAO>();
        EmpleadoDAO empAux;
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
                empAux = new EmpleadoDAO();
                empAux.setIdEmpleado(rs.getInt("emp_no"));
                empAux.setApellido(rs.getString("apellido"));
                empAux.setFechaAlta(rs.getDate("fecha_alt"));
                empAux.setDepartamento(departamentoRepository.depById(rs.getInt("dept_no")));
                //añadimos el objeto creado a la lista de empleados
                listadoEmpleados.add(empAux);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listadoEmpleados;
    }

    //en este caso la comprobación devuelve cero si el usuario no existe, eso lo gestionará el servicio
    public int empleadoByName(String apellido){
        int idEmpleado = 0;

        try(Connection miCon = conBD.conectarDB()){
            //instanciamos un preparedStatement para pasarle el apellido
            PreparedStatement stmtEmpByName = miCon.prepareStatement("select emp_no from empleados where apellido = ?");
            //recuperamos en un ResultSet ejecutando la sentencia con un executeQuery
            stmtEmpByName.setString(1, apellido);
            ResultSet rs = stmtEmpByName.executeQuery();
            //recuperamos el id
            while(rs.next()){
                idEmpleado = rs.getInt("emp_no");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return idEmpleado;
    }

    public String insertEmpleado(EmpleadoDAO nuevoEmpleado) {
        String mensaje = "";

        try(Connection miCon = conBD.conectarDB()){
            //instanciamos un preparedStatement para pasarle el apellido
            PreparedStatement stmtInsertEmp = miCon.prepareStatement("insert into empleados values (?,?,?, ?, ?, ?, ?, ?)");
            //insertamos en la sentencia sql los valores del empleado
            stmtInsertEmp.setInt(1, nuevoEmpleado.getIdEmpleado());
            stmtInsertEmp.setString(2, nuevoEmpleado.getApellido());
            stmtInsertEmp.setString(3, nuevoEmpleado.getOficio());
            stmtInsertEmp.setInt(4, nuevoEmpleado.getIdDirector());
            stmtInsertEmp.setDate(5, nuevoEmpleado.getFechaAlta());
            stmtInsertEmp.setFloat(6, nuevoEmpleado.getSalario());
            stmtInsertEmp.setFloat(7, nuevoEmpleado.getComision());
            stmtInsertEmp.setInt(8, nuevoEmpleado.getDepartamento().getIdDepartamento());
            //y lo ejecutamos actualizando la base de datos
            stmtInsertEmp.executeUpdate();
            mensaje="Empleado insertado con exito";
        } catch (SQLException e) {
            mensaje ="Ocurrio un error al insertar el empleado";
        }

        return mensaje;
    }
}

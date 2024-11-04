package repositories;

import dao.Departamento;
import dataBase.ConnectionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DepartamentoRepository {
    ConnectionBD conBD = new ConnectionBD();
    public ArrayList<Departamento> listarDepartamentos(){
        ArrayList<Departamento> listadoDepartamentos = new ArrayList<Departamento>();
        Departamento depAux = new Departamento();
        try(Connection miCon = conBD.conectarDB()){
            Statement listDepsStatement = miCon.createStatement();
            ResultSet rs = listDepsStatement.executeQuery("select * from departamentos");
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

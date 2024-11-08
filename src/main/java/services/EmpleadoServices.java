package services;

import dao.EmpleadoDAO;
import dto.EmpleadoDTO;
import repositories.DepartamentoRepository;
import repositories.EmpleadoRepository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmpleadoServices {
    private EmpleadoRepository empleadoRepository = new EmpleadoRepository();
    private DepartamentoRepository departamentoRepository = new DepartamentoRepository();
    //para listar empleados recuperamos un listado de DAOs
    //y generamos un array de Strings, que es lo que requiere la interfaz del usuario en este caso.
    //los objetos a transmitir al usuario (DTO: Data Transfer Object) son strings
    public ArrayList<String> listarEmpleados(){
        ArrayList<String> empleadosDTO = new ArrayList<String>();
        ArrayList<EmpleadoDAO> empleadosDAO = empleadoRepository.listarEmpleados();
        String empString;
        //aquí hemos seleccionado mostrar esa información al usuario, pero podría ser otra
        //según definamos nuestros DTOs
        for (EmpleadoDAO empleado : empleadosDAO){
            empString = empleado.getApellido() + ". Fecha de alta: " + empleado.getFechaAlta() +
                    ". Departamento: " + empleado.getDepartamento().getNombre();
            empleadosDTO.add(empString);
        }
        return empleadosDTO;
    }

    public String insertarEmpleado(EmpleadoDTO nuevoEmp){
        //devolvemos un mensaje
        String mensaje="";
        //si se cumplen las restricciones
        boolean restriccionOK = true;
        //llamamos al repositorio pasándole un DAO
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        //comprueba si se va a poder hacer la inserción antes de llamar al repositorio
        //las restricciones de la base de datos deben cumplirse
        //comprobamos que el director existe
        int idDirector = empleadoRepository.empleadoByName(nuevoEmp.getApeDir());
        if (idDirector==0){
            restriccionOK = false;
            mensaje ="El empleado director no existe.\n";
        }
        //comprobamos que el departamento existe
        if (!departamentoRepository.nombreDepExiste(nuevoEmp.getNombreDep())){
            mensaje += "El departamento no existe.\n";
            restriccionOK = false;
        }
        //si se cumplen las restricciones de la base de datos y de la lógica de negocio (en este caso
        //departamento y director no pueden ser null)
        //generamos el DAO a enviar al repositorio para que pueda generar la consulta de inserción
        if (restriccionOK){
            empleadoDAO.setIdEmpleado(nuevoEmp.getIdEmpleado());
            empleadoDAO.setApellido(nuevoEmp.getApellido());
            empleadoDAO.setOficio(nuevoEmp.getOficio());
            empleadoDAO.setIdDirector(idDirector);
            empleadoDAO.setFechaAlta(Date.valueOf(LocalDate.now()));
            empleadoDAO.setSalario(nuevoEmp.getSalario());
            empleadoDAO.setComision(nuevoEmp.getComision());
            empleadoDAO.setDepartamento(departamentoRepository.depByName(nuevoEmp.getNombreDep()));
            mensaje = empleadoRepository.insertEmpleado(empleadoDAO);
        }
        return mensaje;
    }


}

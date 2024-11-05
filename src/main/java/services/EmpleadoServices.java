package services;

import dao.Empleado;
import repositories.EmpleadoRepository;

import java.util.ArrayList;

public class EmpleadoServices {
    private EmpleadoRepository empleadoRepository = new EmpleadoRepository();

    //para listar empleados recuperamos un listado de DAOs
    //y generamos un array de Strings, que es lo que requiere la interfaz del usuario en este caso.
    //los objetos a transmitir al usuario (DTO: Data Transfer Object) son strings
    public ArrayList<String> listarEmpleados(){
        ArrayList<String> empleadosDTO = new ArrayList<String>();
        ArrayList<Empleado> empleadosDAO = empleadoRepository.listarEmpleados();
        String empString;
        //aquí hemos seleccionado mostrar esa información al usuario, pero podría ser otra
        //según definamos nuestros DTOs
        for (Empleado empleado : empleadosDAO){
            empString = empleado.getApellido() + ". Fecha de alta: " + empleado.getFechaAlta() +
                    ". Departamento: " + empleado.getDepartamento().getNombre();
            empleadosDTO.add(empString);
        }
        return empleadosDTO;
    }
}

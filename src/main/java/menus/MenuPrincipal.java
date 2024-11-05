package menus;

import dataBase.GestionDataBase;
import dataBase.GestionTablas;
import libs.Leer;
import services.DepartamentoService;
import services.EmpleadoServices;

import java.util.Scanner;

public class MenuPrincipal {
    private boolean salir = false;
    private Scanner sc = new Scanner(System.in);
    private GestionDataBase db = new GestionDataBase();
    private GestionTablas gestionTablas = new GestionTablas();
    private DepartamentoService departamentoService = new DepartamentoService();
    private EmpleadoServices empleadoServices = new EmpleadoServices();

    public void muestraMenu() {
        String opcion;
        do {
            System.out.println("Elige una opcion:");
            //los 4 primeros puntos son de creación de infraestructura,
            //en una situación normal no serían opciones para el usuario
            System.out.println("1. Crear Base de Datos.");
            System.out.println("2. Borrar Base de Datos.");
            System.out.println("3. Crear Tablas.");
            System.out.println("4. Borrar Tablas.");
            //a partir de aquí los servicios al usuario
            System.out.println("5. Listar departamentos.");
            System.out.println("6. Listar empleados.");
            System.out.println("0. Salir");
            opcion = this.pideOpcion();
            this.procesaOpcion(opcion);
        } while (!salir);
    }

    private String pideOpcion() {
        return this.sc.nextLine();
    }

    private void procesaOpcion(String opcion) {
        switch (opcion) {
            case "0" -> salir = true;
            case "1" -> {
                String nombreDB = Leer.pedirCadena("Introduce el nombre de la base de datos");
                db.crearDB(nombreDB);
                Leer.mostrarEnPantalla("Creada");
                }
            case "2" -> {
                String nombreDB = Leer.pedirCadena("Introduce el nombre de la base de datos");
                db.borrarDB(nombreDB);
                Leer.mostrarEnPantalla("Borrada");
            }
            case "3" -> {
                gestionTablas.crearTablas();
                System.out.println("Tablas creadas");
            }
            case "4" -> {
                gestionTablas.borrarTablas();
                System.out.println("Tablas borradas");
            }
            case "5" -> {
                for (String dep: departamentoService.listarDepartamentos()) {
                    System.out.println(dep);
                }
            }
            case "6" ->{
                for (String emp: empleadoServices.listarEmpleados()){
                    System.out.println(emp);
                }
            }
            default -> System.out.println("Opción incorrecta");
        }
    }
}

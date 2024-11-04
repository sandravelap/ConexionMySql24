package services;

import dao.Departamento;
import repositories.DepartamentoRepository;

import java.util.ArrayList;

public class DepartamentoService {
    private DepartamentoRepository departamentoRepository = new DepartamentoRepository();
    public ArrayList<String> listarDepartamentos(){
        ArrayList<Departamento> listaDepartamentosDAO = departamentoRepository.listarDepartamentos();
        ArrayList<String> listaDepsString = new ArrayList<String>();
        String depString;
        for (Departamento departamento : listaDepartamentosDAO) {
            depString = departamento.getNombre() + ". Localidad: " + departamento.getLocalidad();
            listaDepsString.add(depString);
        }
        return listaDepsString;
    }
}

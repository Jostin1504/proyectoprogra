package Proyecto.Presentation.Historico;

import Proyecto.logic.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
      View view;
      Model model;

    public Controller(View view, Model model) throws Exception {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.addPropertyChangeListener(view);
        model.setPacientes(Service.instance().buscarPacienteCedula(new Paciente()));
    }

    void clear(){
        model.setCurrent(new Paciente());
    }

    //obtener lista de medicos
    public List<Receta> getRecetas() throws Exception {
        return Service.instance().buscarTodasRecetas();
    }

    public void seleccionarPacienteYBuscarRecetas(int row) throws Exception {
        if (row >= 0 && row < model.getPacientes().size()) {
            Paciente selectedPaciente = model.getPacientes().get(row);
            model.setCurrent(selectedPaciente);

            String idPaciente = selectedPaciente.getId();
            List<Receta> recetas = Service.instance().buscarRecetasPorPaciente(idPaciente);
            if (recetas == null) {
                recetas = new ArrayList<>();
            }
            model.setRecetas(recetas);
        }
    }

    public void searchPacienteNombre(String nombre) throws Exception {
        Paciente d = new Paciente();
        d.setNombre(nombre);
        model.setPacientes(Service.instance().buscarPacienteNombre(d));
    }

    public void searchPacienteCedula(String id) throws Exception {
        Paciente d = new Paciente();
        d.setId(id);
        model.setPacientes(Service.instance().buscarPacienteCedula(d));
    }

    public void setCurrent(int row){
        if (row >= 0 && row < model.getPacientes().size()) {
            Paciente selectedPaciente = model.getPacientes().get(row);
            model.setCurrent(selectedPaciente);
        }
    }

    //encontrar medicos en data con nombre
    public void encontrarRecetasPaciente(String id) throws Exception {
        // Buscar el paciente
        Paciente paciente = Service.instance().buscarPaciente(id);
        if (paciente == null) {
            throw new Exception("Paciente con id '" + id + "' no encontrado");
        }
        model.setCurrent(paciente);
        List<Receta> recetas = Service.instance().buscarRecetasPorPaciente(id);
        if (recetas == null) {
            recetas = new ArrayList<>();
        }
        model.setRecetas(recetas);
    }

}

package Proyecto.Presentation.Despacho;

import Proyecto.Logic.Paciente;
import Proyecto.Logic.Service;

import java.util.List;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.addPropertyChangeListener(view);
        model.setRecetas(model.getCurrent().getRecetas());
    }

    public void clear(){
        model.setCurrent(new Paciente());
    }

    public void buscarPaciente(String id){
        try {
            Paciente paciente = Service.instance().buscarPaciente(id);
            model.setCurrent(paciente);
            model.setRecetas(paciente.getRecetas());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Paciente getPaciente(String id){
        try {
            return Service.instance().buscarPaciente(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void guardarEstado(String e){
        model.getReceta().setEstado(e);
        Service.instance().guardarDatos();
    }
}

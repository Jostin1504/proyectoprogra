package Proyecto.Presentation.Despacho;

import Proyecto.Logic.Paciente;
import Proyecto.Logic.Recetas;
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
    }

    public void clear(){
        model.setCurrent(new Recetas());
    }

    public Paciente getPaciente(String id){
        try {
            return Service.instance().buscarPaciente(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

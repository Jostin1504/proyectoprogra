package Proyecto.Presentation.Hospital.Medico;

import Proyecto.Logic.Medico;
import Proyecto.Logic.Service;

import java.util.List;

public class Controller {
    private Proyecto.Presentation.Hospital.Medico.View view;
    private Proyecto.Presentation.Hospital.Medico.Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    void clear(){
        model.setCurrent(new Medico());
    }

    //obtener lista de medicos
    public List<Medico> getMedicos(){
        return Service.instance().getMedicos();
    }

    //guardar medicos en data
    public void guardarMedico(Medico medico){
        Service.instance().anadirMedico(medico);
        model.setCurrent(new Medico());
    }

    //encontrar medicos en data con nombre
    public Medico encontrarMedico(String nom){
        return Service.instance().buscarMedico(nom);
    }

    //borrar medicos de la lista
    public void borrarMedico(Medico medico){
        Service.instance().eliminarMedico(medico);
        model.setCurrent(new Medico());
    }
}

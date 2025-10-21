package Proyecto.Presentation.Medico;

import Proyecto.Logic.Medico;
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
        model.setMedicos(Service.instance().getMedicos());
    }

    public void clear(){
        model.setCurrent(new Medico());
    }

    //obtener lista de medicos
    public List<Medico> getMedicos(){
        return Service.instance().getMedicos();
    }

    //guardar medicos en data
    public void guardarMedico(Medico medico) throws Exception{
        Service.instance().anadirMedico(medico);
        model.setCurrent(new Medico());
        model.setMedicos(Service.instance().getMedicos());
    }

    //encontrar medicos en data con nombre
    public void encontrarMedico(String nom) throws Exception{
        model.setCurrent(Service.instance().buscarMedico(nom));
    }

    //borrar medicos de la lista
    public void borrarMedico(Medico medico) throws Exception {
        Service.instance().eliminarMedico(medico);
        model.setCurrent(new Medico());
    }

    public void setMedicos(){
        model.setMedicos(Service.instance().getMedicos());
    }
}

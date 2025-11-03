package Proyecto.Presentation.Medico;

import Proyecto.logic.Medico;
import Proyecto.logic.Service;

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
        model.setMedicos(Service.instance().getMedicos());
    }

    public void clear(){
        model.setCurrent(new Medico());
    }

    //obtener lista de medicos
    public List<Medico> getMedicos() throws Exception {
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
        Medico f = Service.instance().buscarMedico(nom);
        if (f == null) {
            throw new Exception("Medico con nombre '" + nom + "' no encontrado");
        }
        model.setCurrent(f);
    }

    //borrar medicos de la lista
    public void borrarMedico(Medico medico) throws Exception {
        Service.instance().eliminarMedico(medico);
        model.setCurrent(new Medico());
    }

    public void setMedicos() throws Exception {
        model.setMedicos(Service.instance().getMedicos());
    }
}

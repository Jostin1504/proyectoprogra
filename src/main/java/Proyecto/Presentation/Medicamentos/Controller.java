package Proyecto.Presentation.Medicamentos;

import Proyecto.Logic.Medicamento;
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
        model.setMedicamentos(Service.instance().getMedicamentos());
    }

    public void clear(){
        model.setCurrent(new Medicamento());
    }

    //obtener lista de medicos
    public List<Medicamento> getMedicamentos(){
        return Service.instance().getMedicamentos();
    }

    //guardar medicos en data
    public void guardarMedicamento(Medicamento medico) throws Exception{
        Service.instance().anadirMedicamento(medico);
        model.setCurrent(new Medicamento());
        model.setMedicamentos(Service.instance().getMedicamentos());
    }

    //encontrar medicos en data con nombre
    public void encontrarMedicamento(String nom) throws Exception{
        model.setCurrent(Service.instance().buscarMedicamento(nom));
    }

    //borrar medicos de la lista
    public void borrarMedicamento(Medicamento medico){
        Service.instance().eliminarMedicamento(medico);
        model.setCurrent(new Medicamento());
    }

    public void setMedicamentos(){
        model.setMedicamentos(Service.instance().getMedicamentos());
    }
}

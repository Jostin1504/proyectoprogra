package Proyecto.Presentation.Prescripcion;

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
    }

    void clear(){
        model.setCurrent(new Medicamento());
    }

    //obtener lista de medicos
    public List<Medicamento> getMedicamentos(){
        return Service.instance().getMedicamentos();
    }

    //guardar medicos en data
    public void guardarMedicamento(Medicamento medicamento){
        Service.instance().anadirMedicamento(medicamento);
        model.setCurrent(new Medicamento());
    }

    //encontrar medicos en data con nombre
    public Medicamento encontrarMedicamento(String nom){
        return Service.instance().buscarMedicamento(nom);
    }

    //borrar medicos de la lista
    public void borrarMedicamento(Medicamento medicamento){
        Service.instance().eliminarMedicamento(medicamento);
        model.setCurrent(new Medicamento());
    }
}

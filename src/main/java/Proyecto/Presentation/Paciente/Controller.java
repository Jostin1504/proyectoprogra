package Proyecto.Presentation.Paciente;

import Proyecto.Logic.Paciente;
import Proyecto.Logic.Service;

import java.beans.PropertyChangeListener;
import java.util.List;

public class Controller {
    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.addPropertyChangeListener(view);
        model.setPacientes(Service.instance().getPacientes());
    }

    void clear(){
        model.setCurrent(new Paciente());
    }

    //obtener lista de medicos
    public List<Paciente> getPacientes(){
        return Service.instance().getPacientes();
    }

    //guardar medicos en data
    public void guardarPaciente(Paciente paciente){
        Service.instance().anadirPaciente(paciente);
        model.setCurrent(new Paciente());
        model.setPacientes(Service.instance().getPacientes());
    }

    //encontrar medicos en data con nombre
    public void encontrarPaciente(String nom)throws  Exception{
        model.setCurrent(Service.instance().buscarPaciente(nom));

    }

    //borrar medicos de la lista
    public void borrarPaciente(Paciente paciente){
        Service.instance().eliminarPaciente(paciente);
        model.setCurrent(new Paciente());
    }
}

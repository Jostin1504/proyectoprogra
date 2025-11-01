package Proyecto.Presentation.Paciente;

import Proyecto.logic.Paciente;
import Proyecto.logic.Service;

import java.util.List;

public class Controller {
    private View view;
    private Model model;

    public Controller(View view, Model model) throws Exception {
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
    public List<Paciente> getPacientes() throws Exception {
        return Service.instance().getPacientes();
    }

    //guardar medicos en data
    public void guardarPaciente(Paciente paciente) throws Exception {
        Service.instance().anadirPaciente(paciente);
        model.setCurrent(new Paciente());
        model.setPacientes(Service.instance().getPacientes());
    }

    //encontrar paciente en data con nombre
    public void encontrarPaciente(String nom)throws  Exception{
        model.setCurrent(Service.instance().buscarPaciente(nom));

    }

    //borrar medicos de la lista
    public void borrarPaciente(Paciente paciente) throws Exception {
        Service.instance().eliminarPaciente(paciente);
        model.setCurrent(new Paciente());
    }
}

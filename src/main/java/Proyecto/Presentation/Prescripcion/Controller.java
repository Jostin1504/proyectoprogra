package Proyecto.Presentation.Prescripcion;

import Proyecto.Logic.*;

import java.util.List;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) throws Exception {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.setPacientes(Service.instance().buscarPacienteCedula(new Paciente()));
    }

    void clear(){
        model.setCurrent(new Recetas());
    }

    //obtener lista de medicos
    public List<Medicamento> getMedicamentos(){
        return Service.instance().getMedicamentos();
    }


    //encontrar medicos en data con nombre
    public Medicamento encontrarMedicamento(String nom) throws Exception {
            return Service.instance().buscarMedicamento(nom);
    }

    public Paciente buscarPaciente(String nom) throws Exception {
        return Service.instance().buscarPaciente(nom);
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

    public void setPaciente(int row){
        model.setPaciente(model.getPacientes().get(row));
    }

}

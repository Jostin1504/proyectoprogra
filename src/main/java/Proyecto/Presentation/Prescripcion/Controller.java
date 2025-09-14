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

    public void searchMedNombre(String nombre) throws Exception {
        Medicamento d = new Medicamento();
        d.setNombre(nombre);
        model.setMedicamentos(Service.instance().buscarMedNombre(d));
    }

    public void searchPacienteCedula(String id) throws Exception {
        Paciente d = new Paciente();
        d.setId(id);
        model.setPacientes(Service.instance().buscarPacienteCedula(d));
    }

    public void searchMedCodigo(String id) throws Exception {
        Medicamento d = new Medicamento();
        d.setCodigo(id);
        model.setMedicamentos(Service.instance().buscarMedCodigo(d));
    }

    public void setPaciente(int row){
        model.setPaciente(model.getPacientes().get(row));
    }
    public void setMedicamento(int row){
        // Este método debería seleccionar un medicamento específico
        // según lo que necesites hacer con él
        Medicamento selected = model.getMedicamentos().get(row);
        // Aquí puedes agregar la lógica específica para manejar el medicamento seleccionado
        // Por ejemplo, agregarlo a la receta actual:
        model.addMedicamentoToReceta(selected); // Usar el método público del modelo
    }

    public void setMed(int row){
        if (row >= 0 && row < model.getMedicamentos().size()) {
            setMedicamento(row);
        }
    }

}

package Proyecto.Presentation.Prescripcion;

import Proyecto.Logic.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        model.setMedicamentos(Service.instance().getMedicamentos());
    }

    void clear(){
        model.setCurrent(new Recetas());
        model.setCurrentPaciente(null);
    }

    public List<Medicamento> getMedicamentos(){
        return Service.instance().getMedicamentos();
    }

    public void seleccionarMedicamento(int row) {
        if (row >= 0 && row < model.getMedicamentos().size()) {
            Medicamento selected = model.getMedicamentos().get(row);
            model.setCurrentMedicamento(selected);
        }
    }

    public Medicamento encontrarMedicamento(String codigo) throws Exception {
        return Service.instance().buscarMedicamento(codigo);
    }

    public Paciente buscarPaciente(String id) throws Exception {
        return Service.instance().buscarPaciente(id);
    }

    public void searchPacienteNombre(String nombre) throws Exception {
        Paciente d = new Paciente();
        d.setNombre(nombre);
        model.setPacientes(Service.instance().buscarPacienteNombre(d));
    }

    public void editarDetalle(String cantidad, String duracion, String detalles){
        if (model.getCurrentMedicamento() != null) {
            model.getCurrentMedicamento().setDuracion(Integer.parseInt(duracion));
            model.getCurrentMedicamento().setCantidad(Integer.parseInt(cantidad));
            model.getCurrentMedicamento().setIndicaciones(detalles);
        }
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
        if (row >= 0 && row < model.getPacientes().size()) {
            Paciente selectedPaciente = model.getPacientes().get(row);
            model.setCurrentPaciente(selectedPaciente);
        }
    }

    public void setMedicamento(int row){
        if (row >= 0 && row < model.getMedicamentos().size()) {
            Medicamento selected = model.getMedicamentos().get(row);
            model.setCurrentMedicamento(selected);
        }
    }

    public void setMed(int row){
        if (row >= 0 && row < model.getMedicamentos().size()) {
            Medicamento selected = model.getMedicamentos().get(row);
            model.setCurrentMedicamento(selected);
        }
    }

    public void guardarReceta(String fechaRetiro) throws Exception {
        if (model.getCurrentPaciente() == null) {
            throw new Exception("Debe seleccionar un paciente");
        }
        if (model.getCurrent().getMedicamentos().isEmpty()) {
            throw new Exception("La receta debe tener al menos un medicamento");
        }
        if (fechaRetiro == null || fechaRetiro.trim().isEmpty()) {
            throw new Exception("Debe seleccionar una fecha de retiro");
        }
        model.getCurrent().setFechaRetiro(fechaRetiro);
        model.getCurrent().setFechaCreacion(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        model.guardarRecetaAPaciente();
        Service.instance().actualizarPaciente(model.getCurrentPaciente());
        clear();
    }

    public List<Recetas> getRecetasPaciente(String idPaciente) throws Exception {
        Paciente paciente = Service.instance().buscarPaciente(idPaciente);
        return paciente.getRecetas();
    }
}
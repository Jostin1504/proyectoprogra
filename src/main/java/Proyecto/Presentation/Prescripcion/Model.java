package Proyecto.Presentation.Prescripcion;

import Proyecto.Logic.*;
import Proyecto.Presentation.AbstractModel;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    Recetas current;
    List<Recetas> list;
    List<Paciente> pacientes;
    List<Medicamento> medicamentos;

    public static final String CURRENT = "current";
    public static final String LIST = "list";
    public static final String PACIENTES = "pacientes";
    public static final String PACIENTE = "paciente";
    public static final String MEDICAMENTOS = "medicamentos";
    public static final String MEDICAMENTO = "medicamento";

    public Model() {
        current = new Recetas();
        list = new ArrayList<Recetas>();
        pacientes = new ArrayList<Paciente>();
        medicamentos = new ArrayList<Medicamento>();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
        firePropertyChange(LIST);
        firePropertyChange(PACIENTES);
        firePropertyChange(PACIENTE);
        firePropertyChange(MEDICAMENTOS);
        firePropertyChange(MEDICAMENTO);
    }

    public Recetas getCurrent() {
        return current;
    }

    public void setCurrent(Recetas current) {
        this.current = current;
        firePropertyChange(CURRENT);
        firePropertyChange(PACIENTE);
        firePropertyChange(MEDICAMENTO);
    }

    public List<Recetas> getList() {
        return list;
    }

    public void setList(List<Recetas> list) {
        this.list = list;
        firePropertyChange(LIST);
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setPacientes(List<Paciente> departamentos) {
        this.pacientes = departamentos;
        firePropertyChange(PACIENTES);
    }


    public void setPaciente(Paciente departamento) {
        this.current.setPaciente(departamento);
        firePropertyChange(PACIENTES);
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
        firePropertyChange(MEDICAMENTOS);
    }

    // Este método agrega un medicamento específico a la receta actual
    public void addMedicamentoToReceta(Medicamento medicamento) {
        this.current.getMedicamentos().add(medicamento);
        firePropertyChange(MEDICAMENTO);
    }

    // Método para seleccionar un medicamento específico
    public void setSelectedMedicamento(Medicamento medicamento) {
        // Aquí puedes manejar la selección de un medicamento específico
        // según las necesidades de tu aplicación
        firePropertyChange(MEDICAMENTO);
    }

    // Método público para notificar cambios desde el controller si es necesario
    public void notifyMedicamentoChange() {
        firePropertyChange(MEDICAMENTO);
    }

}

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
    Medicamento currentMed;
    Paciente currentPaciente;

    public static final String CURRENT = "current";
    public static final String LIST = "list";
    public static final String PACIENTES = "pacientes";
    public static final String PACIENTE = "paciente";
    public static final String MEDICAMENTOS = "medicamentos";
    public static final String MEDICAMENTO = "medicamento";
    public static final String CURRENTMED = "currentmed";

    public Model() {
        current = new Recetas();
        list = new ArrayList<Recetas>();
        pacientes = new ArrayList<Paciente>();
        medicamentos = new ArrayList<Medicamento>();
        currentMed = new Medicamento();
        currentPaciente = null;
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
        firePropertyChange(CURRENTMED);
    }

    public Recetas getCurrent() {
        return current;
    }

    public void setCurrent(Recetas current) {
        this.current = current;
        firePropertyChange(CURRENT);
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

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
        firePropertyChange(PACIENTES);
    }

    public Paciente getCurrentPaciente() {
        return currentPaciente;
    }

    public void setCurrentPaciente(Paciente paciente) {
        this.currentPaciente = paciente;
        if (paciente != null) {
            this.current.setIdPaciente(paciente.getId());
        } else {
            this.current.setIdPaciente("");
        }
        firePropertyChange(PACIENTE);
        firePropertyChange(CURRENT);
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
        firePropertyChange(MEDICAMENTOS);
    }

    public void addMedicamentoToReceta(Medicamento medicamento) {
        this.current.getMedicamentos().add(medicamento);
        firePropertyChange(MEDICAMENTO);
        firePropertyChange(CURRENT);
    }

    public Medicamento getCurrentMedicamento() {
        return currentMed;
    }

    public void setCurrentMedicamento(Medicamento medicamento) {
        this.currentMed = medicamento;
        firePropertyChange(CURRENTMED);
    }

    public void clearCurrentMedicamento() {
        this.currentMed = new Medicamento();
        firePropertyChange(CURRENTMED);
    }

    public void guardarRecetaAPaciente() {
        if (currentPaciente != null && current != null) {
            Recetas nuevaReceta = new Recetas();
            nuevaReceta.setIdPaciente(currentPaciente.getId());
            nuevaReceta.setFechaRetiro(current.getFechaRetiro());
            nuevaReceta.setFechaCreacion(current.getFechaCreacion());
            nuevaReceta.setMedicamentos(new ArrayList<>(current.getMedicamentos()));
            currentPaciente.agregarReceta(nuevaReceta);
            current = new Recetas();
            firePropertyChange(CURRENT);
            firePropertyChange(PACIENTE);
        }
    }
}

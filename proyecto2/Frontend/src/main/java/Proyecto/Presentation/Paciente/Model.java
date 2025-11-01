package Proyecto.Presentation.Paciente;

import Proyecto.logic.*;
import Proyecto.Presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    private Paciente current;

    private List<Paciente> pacientes;

    public static final String CURRENT = "current";
    public static final String PACIENTES = "pacientes";

    public Model() {
        current = new Paciente();
        pacientes = new ArrayList<>();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
        firePropertyChange(PACIENTES);
    }

    public Paciente getCurrent() {
        return current;
    }

    public void setCurrent(Paciente current) {
        this.current = current;
        firePropertyChange(CURRENT);
    }

    public List<Paciente> getPaciente() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
        firePropertyChange(PACIENTES);
    }
}

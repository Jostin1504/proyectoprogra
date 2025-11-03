package Proyecto.Presentation.Historico;

import Proyecto.Presentation.AbstractModel;
import Proyecto.logic.*;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    private Paciente current;
    private List<Paciente> pacientes;
    private List<Receta> recetas;

    public static final String CURRENT = "current";
    public static final String RECETAS = "recetas";
    public static final String PACIENTES = "pacientes";

    public Model() {
        current = new Paciente();
        recetas = new ArrayList<>();
        pacientes = new ArrayList<Paciente>();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
        firePropertyChange(RECETAS);
        firePropertyChange(PACIENTES);
    }

    public Paciente getCurrent() {
        return current;
    }

    public void setCurrent(Paciente current) {
        this.current = current;
        firePropertyChange(CURRENT);
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
        firePropertyChange(PACIENTES);
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> medicos) {
        this.recetas = medicos;
        firePropertyChange(RECETAS);
    }
}

package Proyecto.Presentation.Despacho;

import Proyecto.Logic.Paciente;
import Proyecto.Logic.Recetas;
import Proyecto.Presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    Paciente current;
    List<Recetas> recetas;

    public static final String CURRENT = "Current";
    public static final String RECETAS = "Recetas";

    public Model() {
        this.current = new Paciente();
        this.recetas = current.getRecetas();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
        firePropertyChange(RECETAS);
    }

    public Paciente getCurrent() {
        return current;
    }

    public void setCurrent(Paciente current) {
        this.current = current;
        firePropertyChange(CURRENT);
        firePropertyChange(RECETAS);
    }

    public List<Recetas> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Recetas> recetas) {
        this.recetas = recetas;
        firePropertyChange(RECETAS);
    }
}

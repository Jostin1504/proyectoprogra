package Proyecto.Presentation.Historico;

import Proyecto.Presentation.AbstractModel;
import Proyecto.logic.*;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    private Paciente current;

    private List<Receta> recetas;

    public static final String CURRENT = "current";
    public static final String RECETAS = "recetas";

    public Model() {
        current = new Paciente();
        recetas = new ArrayList<>();
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
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> medicos) {
        this.recetas = medicos;
        firePropertyChange(RECETAS);
    }
}

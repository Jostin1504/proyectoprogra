package Proyecto.Presentation.Despacho;

import Proyecto.Presentation.AbstractModel;
import Proyecto.logic.*;
import java.beans.PropertyChangeListener;
import java.util.List;

public class Model extends AbstractModel {
    Paciente current;
    List<Receta> recetas;
    Receta receta;

    public static final String CURRENT = "Current";
    public static final String RECETAS = "Recetas";
    public static final String RECETA = "Receta";

    public Model() {
        this.current = new Paciente();
        this.recetas = current.getRecetas();
        this.receta = null;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
        firePropertyChange(RECETAS);
        firePropertyChange(RECETA);
    }

    public Paciente getCurrent() {
        return current;
    }

    public void setCurrent(Paciente current) {
        this.current = current;
        this.recetas = current.getRecetas();
        this.recetas = null;
        firePropertyChange(CURRENT);
        firePropertyChange(RECETAS);
    }

    public Receta getReceta(){
        return receta;
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
        firePropertyChange(RECETAS);
    }
    public void setReceta(Receta receta) {
        this.receta = receta;
        firePropertyChange(RECETA);
    }
}

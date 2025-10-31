package Proyecto.Presentation.Medico;

import Proyecto.logic.*;
import Proyecto.Presentation.AbstractModel;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    Medico current;
    List<Medico> medicos;

    public static final String CURRENT = "current";
    public static final String MEDICOS = "medicos";

    public Model() {
        current = new Medico();
        medicos = new ArrayList<>();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
        firePropertyChange(MEDICOS);
    }

    public Medico getCurrent() {
        return current;
    }

    public void setCurrent(Medico current) {
        this.current = current;
        firePropertyChange(CURRENT);
        firePropertyChange(MEDICOS);
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
        firePropertyChange(MEDICOS);
    }
}

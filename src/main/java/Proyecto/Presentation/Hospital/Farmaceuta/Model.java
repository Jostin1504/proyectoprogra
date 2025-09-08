package Proyecto.Presentation.Hospital.Farmaceuta;

import Proyecto.Logic.Farmaceuta;
import Proyecto.Presentation.Hospital.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    private Farmaceuta current;

    private List<Farmaceuta> farmaceutas;

    public static final String CURRENT = "current";
    public static final String FARMACEUTAS = "farmaceutas";

    public Model() {
        current = new Farmaceuta();
        farmaceutas = new ArrayList<>();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
        firePropertyChange(FARMACEUTAS);
    }

    public Farmaceuta getCurrent() {
        return current;
    }

    public void setCurrent(Farmaceuta current) {
        this.current = current;
        firePropertyChange(CURRENT);
    }

    public List<Farmaceuta> getFarmaceutas() {
        return farmaceutas;
    }

    public void setFarmaceutas(List<Farmaceuta> medicos) {
        this.farmaceutas = medicos;
        firePropertyChange(FARMACEUTAS);
    }
}

package Proyecto.Presentation.Medicamentos;

import Proyecto.Logic.Medicamento;
import Proyecto.Logic.Medico;
import Proyecto.Presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    Medicamento current;
    List<Medicamento> medicamentos;

    public static final String CURRENT = "current";
    public static final String MEDICAMENTOS = "medicamentos";

    public Model() {
        current = new Medicamento();
        medicamentos = new ArrayList<>();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
        firePropertyChange(MEDICAMENTOS);
    }

    public Medicamento getCurrent() {
        return current;
    }

    public void setCurrent(Medicamento current) {
        this.current = current;
        firePropertyChange(CURRENT);
        firePropertyChange(MEDICAMENTOS);
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicos) {
        this.medicamentos = medicamentos;
        firePropertyChange(MEDICAMENTOS);
    }
}

package Proyecto.Presentation.Dashboard;

import Proyecto.Logic.Medicamento;
import Proyecto.Presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    // Cambiar a Object para poder disparar eventos incluso con null
    Object chart1Update;
    Object chart2Update;
    List<Medicamento> medicamentos;

    public Model() {
        chart1Update = null;
        chart2Update = null;
        medicamentos = new ArrayList<>();
    }

    public static final String CHART1 = "chart1";
    public static final String CHART2 = "chart2";
    public static final String MEDICAMENTOS = "medicamentos";

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CHART1);
        firePropertyChange(CHART2);
        firePropertyChange(MEDICAMENTOS);
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
        firePropertyChange(MEDICAMENTOS);
    }

    public void setChart1(Object trigger) {
        this.chart1Update = trigger;
        firePropertyChange(CHART1);
    }

    public void setChart2(Object trigger) {
        this.chart2Update = trigger;
        firePropertyChange(CHART2);
    }
}

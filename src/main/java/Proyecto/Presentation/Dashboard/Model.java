package Proyecto.Presentation.Dashboard;

import Proyecto.Logic.Medicamento;
import Proyecto.Presentation.AbstractModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    JFreeChart chart1;
    JFreeChart chart2;
    List<Medicamento> medicamentos;

    public Model() {
        chart1 = null;
        chart2 = null;
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
    }

    public JFreeChart getChart1() {
        return chart1;
    }

    public void setChart1(JFreeChart chart1) {
        this.chart1 = chart1;
    }

    public JFreeChart getChart2() {
        return chart2;
    }

    public void setChart2(JFreeChart chart2) {
        this.chart2 = chart2;
    }
}

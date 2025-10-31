package Proyecto.Presentation.Dashboard;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import java.awt.*;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    Controller controller;
    Model model;

    private JPanel mainPanelDashboard;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JTable medicamentos;
    private JPanel panelMedicamentos;
    private JPanel panelRecetas;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.MEDICAMENTOS:
                int[] cols = {TableModel.NOMBRE};
                medicamentos.setModel(new TableModel(cols,model.getMedicamentos()));
                break;
        }
        this.mainPanelDashboard.revalidate();
    }

    public JPanel getMainPanelDashboard() {
        return mainPanelDashboard;
    }
}

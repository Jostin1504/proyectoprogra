package Proyecto.Presentation.Dashboard;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;

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
            case Model.CHART1:
                JFreeChart chart1 = ChartFactory.createLineChart("Medicamentos","Mes",
                        "Cantidad", controller.createDataset(model.getMedicamentos()), PlotOrientation.VERTICAL,
                        true, true, false);
                //CategoryPlot plot = (CategoryPlot) chart.getPlot();
                //XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
                //renderer.setBaseShapesVisible(true);
                ChartPanel chartPanel1 = new ChartPanel(chart1);
                panelMedicamentos.removeAll();
                panelMedicamentos.add(chartPanel1);
                break;
            case Model.CHART2:
                JFreeChart chart2 = ChartFactory.createPieChart("Recetas", controller.createPieDataset(), true, true, true);
                ChartPanel chartPanel2 = new ChartPanel(chart2);
                chartPanel2.setLayout(null);
                chartPanel2.setBounds(10, 10, 200, 200);
                panelRecetas.removeAll();
                panelRecetas.add(chartPanel2);
                break;
        }
        this.mainPanelDashboard.revalidate();
    }

    public JPanel getMainPanelDashboard() {
        return mainPanelDashboard;
    }
}

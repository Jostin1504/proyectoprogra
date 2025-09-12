package Proyecto.Presentation.Dashboard;

import Proyecto.Logic.Medicamento;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.*;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

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
            /*case Model.CHART1:
                JFreeChart chart = ChartFactory.createLineChart("Medicamentos","Mes", "Cantidad", controller.createDataset(model.getMedicamentos()), PlotOrientation.VERTICAL, true, true, false);
                CategoryPlot plot = (CategoryPlot) chart.getPlot();
                XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
                //renderer.setBaseShapesVisible(true);
                ChartPanel chartPanel = new ChartPanel(chart, 400, 320, 10, 10,
                1000, 1000, true, true, true, true, true, true);
                panelMedicamentos.removeAll();
                panelMedicamentos.add(chartPanel);

                break;*/
            case Model.CHART2:

                break;
        }
        this.mainPanelDashboard.revalidate();
    }

    public JPanel getMainPanelDashboard() {
        return mainPanelDashboard;
    }
}

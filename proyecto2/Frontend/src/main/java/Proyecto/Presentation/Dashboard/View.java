package Proyecto.Presentation.Dashboard;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private Controller controller;
    private Model model;

    private JPanel mainPanelDashboard;
    private JComboBox comboBox1;  // Año inicio
    private JComboBox comboBox2;  // Mes inicio
    private JComboBox comboBox3;  // Año fin
    private JComboBox comboBox4;  // Mes fin
    private JComboBox comboBox5;  // Medicamentos disponibles
    private JButton button1;      // Agregar medicamento
    private JButton button2;      // Agregar todos
    private JButton button3;      // Remover medicamento
    private JButton button4;      // Actualizar gráficos
    private JTable medicamentos;  // Tabla de medicamentos seleccionados
    private JPanel panelMedicamentos;  // Gráfico de línea
    private JPanel panelRecetas;       // Gráfico de pastel

    public View() {
        inicializarComboBoxFechas();
    }

    /**
     * Inicializa los ComboBoxes de fechas con valores
     */
    private void inicializarComboBoxFechas() {
        // comboBox3 (Año fin) y comboBox4 (Mes fin) también necesitan valores
        if (comboBox3 != null && comboBox3.getModel().getSize() == 0) {
            DefaultComboBoxModel<String> modelAñoFin = new DefaultComboBoxModel<>();
            modelAñoFin.addElement("2024");
            modelAñoFin.addElement("2025");
            modelAñoFin.addElement("2026");
            modelAñoFin.addElement("2027");
            modelAñoFin.addElement("2028");
            comboBox3.setModel(modelAñoFin);
        }

        if (comboBox4 != null && comboBox4.getModel().getSize() == 0) {
            DefaultComboBoxModel<String> modelMesFin = new DefaultComboBoxModel<>();
            String[] meses = {
                    "11-septiembre", "12-septiembre", "13-septiembre", "14-septiembre",
                    "15-septiembre", "16-septiembre", "17-septiembre", "18-septiembre",
                    "19-septiembre", "20-septiembre", "21-septiembre", "22-septiembre",
                    "23-septiembre", "24-septiembre", "25-septiembre", "26-septiembre",
                    "27-septiembre", "28-septiembre", "29-septiembre", "30-septiembre",
                    "01-octubre", "02-octubre", "03-octubre", "04-octubre",
                    "05-octubre", "06-octubre", "07-octubre"
            };
            for (String mes : meses) {
                modelMesFin.addElement(mes);
            }
            comboBox4.setModel(modelMesFin);
            comboBox4.setSelectedIndex(meses.length - 1); // Seleccionar último mes
        }
    }

    // ==================== GETTERS ====================

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

    public JPanel getMainPanelDashboard() {
        return mainPanelDashboard;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public JComboBox getComboBox2() {
        return comboBox2;
    }

    public JComboBox getComboBox3() {
        return comboBox3;
    }

    public JComboBox getComboBox4() {
        return comboBox4;
    }

    public JComboBox getComboBox5() {
        return comboBox5;
    }

    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public JButton getButton3() {
        return button3;
    }

    public JButton getButton4() {
        return button4;
    }

    public JTable getMedicamentos() {
        return medicamentos;
    }

    public JPanel getPanelMedicamentos() {
        return panelMedicamentos;
    }

    public JPanel getPanelRecetas() {
        return panelRecetas;
    }

    // ==================== PROPERTY CHANGE LISTENER ====================

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.MEDICAMENTOS:
                // Los medicamentos disponibles cambiaron (no hace falta actualizar tabla aquí)
                break;

            case Model.MEDICAMENTOS_SELECCIONADOS:
                // Actualizar tabla con medicamentos seleccionados
                int[] cols = {TableModel.NOMBRE, TableModel.PRESENTACION};
                medicamentos.setModel(new TableModel(cols, model.getMedicamentosSeleccionados()));
                medicamentos.revalidate();
                medicamentos.repaint();
                break;

            case Model.CHART1:
                // El gráfico 1 fue actualizado (ya se maneja en el controller)
                break;

            case Model.CHART2:
                // El gráfico 2 fue actualizado (ya se maneja en el controller)
                break;
        }
        this.mainPanelDashboard.revalidate();
        this.mainPanelDashboard.repaint();
    }
}

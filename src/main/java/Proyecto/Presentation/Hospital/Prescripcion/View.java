package Proyecto.Presentation.Hospital.Prescripcion;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private Controller controller;
    private Model model;
    private JPanel mainPanelPrescripcion;
    private JButton agregarMedicamentoButton;
    private JButton buscarPacienteButton;
    private JTable table1;
    private JButton detallesButton;
    private JButton descartarMedicamentoButton;
    private JButton limpiarButton;
    private JButton guardarButton;


    public void setModel(Model model) {
        this.model = model;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {

        }
    }
    public JPanel getMainPanelPrescripcion(){
        return mainPanelPrescripcion;
    }
}

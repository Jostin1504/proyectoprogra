package Proyecto.Presentation.Prescripcion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private Proyecto.Presentation.Prescripcion.BuscarPaciente.View pacienteView;

    public View() {
        pacienteView = new Proyecto.Presentation.Prescripcion.BuscarPaciente.View();

        buscarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pacienteView.setVisible(true);
            }
        });

    }

    public void setModel(Model model) {
        this.model = model;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.CURRENT:

        }
    }
    public JPanel getMainPanelPrescripcion(){
        return mainPanelPrescripcion;
    }
}

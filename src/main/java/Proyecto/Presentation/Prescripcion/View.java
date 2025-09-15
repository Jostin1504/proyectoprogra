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
    private Proyecto.Presentation.Prescripcion.AgregarMedicamento.View medView;


    public View() {
        descartarMedicamentoButton.setEnabled(false);
        detallesButton.setEnabled(false);

        buscarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pacienteView = new Proyecto.Presentation.Prescripcion.BuscarPaciente.View();
                pacienteView.setController(controller);
                pacienteView.setModel(model);
                pacienteView.setVisible(true);
            }
        });

        agregarMedicamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    medView = new Proyecto.Presentation.Prescripcion.AgregarMedicamento.View();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                medView.setController(controller);
                medView.setModel(model);
                medView.setVisible(true);
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

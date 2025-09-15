package Proyecto.Presentation.Despacho;

import Proyecto.Application;
import Proyecto.Logic.Paciente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    Model model;
    Controller controller;

    private JPanel mainPanelDespacho;
    private JButton actualizarEstadoButton;
    private JComboBox comboBoxEstado;
    private JTextField idPacFld;
    private JButton buscarRecetaButton;

    public View() {
        actualizarEstadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validate()){

                }
            }
        });
        buscarRecetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idPacFld.getText() != null) {
                    Paciente paciente = controller.getPaciente(idPacFld.getText());
                    model.setCurrent(paciente);
                }
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
        if (evt.getPropertyName().equals(Model.CURRENT)) {
            idPacFld.setText(model.getCurrent().getNombre());
        }
        this.mainPanelDespacho.revalidate();
    }

    private boolean validate() {
        boolean valid = true;
        if (comboBoxEstado.isValid()) {
            valid = false;
            comboBoxEstado.setBackground(Application.BACKGROUND_ERROR);
        } else {
            comboBoxEstado.setBackground(null);
        }
        return valid;
    }

    public JPanel getMainPanelDespacho() {
        return mainPanelDespacho;
    }
}

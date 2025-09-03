package Proyecto.Presentation.Hospital.Login;

import Proyecto.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel logPanel;
    private JTextField idFld;
    private JPasswordField claveFld;
    private JButton closeButton;
    private JButton enterButton;
    private JButton changePaswButton;

    public View() {


        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validate();
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
            }
        });
        changePaswButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    Controller controller;
    Model model;

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Inside Proyecto.Presentation.Hospital.Login.View.propertyChange(PropertyChangeEvent event)
        Object newValue = evt.getNewValue();
        if (newValue != null) {
            switch (evt.getPropertyName()) {
                case Model.CURRENT:
                    idFld.setText(evt.getNewValue().toString());
                    claveFld.setText(evt.getNewValue().toString());
                    break;
            }
        }
    }

    public JPanel getLogPanel() {
        return logPanel;
    }

    private boolean validate() {
        boolean valid = true;
        if (idFld.getText().isEmpty()) {
            valid = false;
            idFld.setBackground(Application.BACKGROUND_ERROR);
            idFld.setToolTipText("id requerido");
        } else {
            idFld.setBackground(null);
            idFld.setToolTipText(null);
        }
        if (claveFld.getText().isEmpty()) {
            valid = false;
            claveFld.setBackground(Application.BACKGROUND_ERROR);
            claveFld.setToolTipText("clave requerida");
        } else {
            claveFld.setBackground(null);
            claveFld.setToolTipText(null);
        }

        return valid;
    }
}

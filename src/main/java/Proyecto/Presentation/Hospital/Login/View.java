package Proyecto.Presentation.Hospital.Login;

import Proyecto.Application;
import Proyecto.Logic.Usuario;

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
    private JLabel mensajeLabel; // Para mostrar mensajes de estado

    private Controller controller;
    private Model model;

    public View() {
        // Configurar los listeners de los botones
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    controller.entrar();
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.salir();
            }
        });

        changePaswButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.cambiarClave();
            }
        });
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.CURRENT:
                Usuario current = (Usuario) evt.getNewValue();
                if (current != null) {
                    if (current.getCedula().isEmpty()) {
                        idFld.setText("");
                        claveFld.setText("");
                        limpiarValidacion();
                    }
                }
                break;

            case Model.MENSAJE:
                String mensaje = (String) evt.getNewValue();
                if (mensaje != null && !mensaje.isEmpty()) {
                    if (mensaje.contains("exitoso") || mensaje.contains("Bienvenido")) {
                        JOptionPane.showMessageDialog(
                                logPanel,
                                mensaje,
                                "Éxito",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                logPanel,
                                mensaje,
                                "Información",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
                }
                break;
        }
    }

    public JPanel getLogPanel() {
        return logPanel;
    }

    public String getId() {
        return idFld.getText().trim();
    }

    public String getClave() {
        return new String(claveFld.getPassword()).trim();
    }

    private boolean validate() {
        boolean valid = true;

        if (idFld.getText().trim().isEmpty()) {
            valid = false;
            idFld.setBackground(Application.BACKGROUND_ERROR);
            idFld.setToolTipText("ID es requerido");
        } else {
            idFld.setBackground(null);
            idFld.setToolTipText(null);
        }

        if (new String(claveFld.getPassword()).trim().isEmpty()) {
            valid = false;
            claveFld.setBackground(Application.BACKGROUND_ERROR);
            claveFld.setToolTipText("Clave es requerida");
        } else {
            claveFld.setBackground(null);
            claveFld.setToolTipText(null);
        }

        return valid;
    }

    private void limpiarValidacion() {
        idFld.setBackground(null);
        idFld.setToolTipText(null);
        claveFld.setBackground(null);
        claveFld.setToolTipText(null);
    }

    public JButton getEnterButton() {
        return enterButton;
    }
}

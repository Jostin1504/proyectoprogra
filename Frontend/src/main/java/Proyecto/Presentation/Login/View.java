package Proyecto.Presentation.Login;

import Proyecto.Application;
import Proyecto.logic.Usuario;

import java.awt.Frame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View extends JDialog implements PropertyChangeListener {
    private JPanel logPanel;
    private JTextField idFld;
    private JPasswordField claveFld;
    private JButton closeButton;
    private JButton enterButton;
    private JButton changePaswButton;
    private JLabel mensajeLabel; // Para mostrar mensajes de estado
    private Controller controller;
    private Model model;

    public View(Frame parent, boolean modal) {
        super(parent, modal);
        setContentPane(logPanel);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validar()) {
                    String idNow = idFld.getText();
                    String claveNow = claveFld.getText();
                    Usuario usuario = new Usuario("", idNow, "");
                    usuario.setClave(claveNow);
                    controller.login(usuario);
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
                    if (mensaje.toLowerCase().contains("exitoso") ||
                            mensaje.toLowerCase().contains("bienvenido") ||
                            mensaje.toLowerCase().contains("cambiada exitosamente")) {

                        JOptionPane.showMessageDialog(
                                this,
                                mensaje,
                                "Éxito",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                    } else if (mensaje.toLowerCase().contains("error") ||
                            mensaje.toLowerCase().contains("incorrectos") ||
                            mensaje.toLowerCase().contains("no existe") ||
                            mensaje.toLowerCase().contains("no se pudo")) {

                        JOptionPane.showMessageDialog(
                                this,
                                mensaje,
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );

                    } else if (mensaje.toLowerCase().contains("cancelada") ||
                            mensaje.toLowerCase().contains("no coinciden")) {

                        JOptionPane.showMessageDialog(
                                this,
                                mensaje,
                                "Advertencia",
                                JOptionPane.WARNING_MESSAGE
                        );

                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                mensaje,
                                "Información",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }

                    model.setMensaje("");
                }
                break;

            default:
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

    private boolean validar() {
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

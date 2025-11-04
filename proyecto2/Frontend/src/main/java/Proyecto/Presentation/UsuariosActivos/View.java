package Proyecto.Presentation.UsuariosActivos;

import Proyecto.logic.*;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private Model model;
    private Controller controller;
    private JPanel mainPanel;
    private JTable usuarios;
    private JButton enviarButton;
    private JButton recibirButton;
    private JLabel usuariosLabel;

    public View() {
        // Los botones por ahora no hacen nada
        enviarButton.setEnabled(false);
        recibirButton.setEnabled(false);
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
            case Model.ACTIVE_USERS:
                int[] cols = {TableModel.ID};
                usuarios.setModel(new TableModel(cols, model.getActiveUsers()));
                break;
        }
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}

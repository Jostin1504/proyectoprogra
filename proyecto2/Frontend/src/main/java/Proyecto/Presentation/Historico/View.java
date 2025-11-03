package Proyecto.Presentation.Historico;

import Proyecto.Presentation.Historico.TableModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private Controller controller;
    private Model model;
    private JPanel mainPanelHistorico;
    private JTextField name2Fld;
    private JButton buscarButton;
    private JButton limpiarButton;
    private JTable recetas;

    public View() {
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.encontrarRecetasPaciente(name2Fld.getText());
                    JOptionPane.showMessageDialog(mainPanelHistorico, "Recetas encontradas");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(mainPanelHistorico, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        switch (evt.getPropertyName()) {
            case Model.CURRENT:
                name2Fld.setText(model.getCurrent().getNombre());
                int[] cols = { TableModel.ID, TableModel.FECHARET, TableModel.MEDICAMENTOS, TableModel.ESTADO};
                recetas.setModel(new TableModel(cols, model.getCurrent().getRecetas()));
                break;
            case Model.RECETAS:

        }
    }
     public JPanel getMainPanelHistorico() {
        return mainPanelHistorico;
    }
}

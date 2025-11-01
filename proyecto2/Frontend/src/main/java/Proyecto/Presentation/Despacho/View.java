package Proyecto.Presentation.Despacho;

import Proyecto.logic.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    Model model;
    Controller controller;
    private JPanel mainPanelDespacho;
    private JButton procesoButton;
    private JTextField idPacFld;
    private JButton buscarRecetaButton;
    private JTable recetas;
    private JButton listaButton;
    private JButton entregadaButton;

    public View() {
        procesoButton.setEnabled(false);
        listaButton.setEnabled(false);
        entregadaButton.setEnabled(false);
        buscarRecetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idPacFld.getText() != null && !idPacFld.getText().isEmpty()) {
                    try {
                        Paciente paciente = controller.getPaciente(idPacFld.getText());
                        model.setCurrent(paciente);
                        model.setRecetas(paciente.getRecetas());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(mainPanelDespacho,
                                "Paciente no encontrado: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        recetas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = recetas.getSelectedRow();
                if (selectedRow >= 0) {
                    TableModel tableModel = (TableModel) recetas.getModel();
                    Receta equipoSeleccionado = tableModel.getRowAt(selectedRow);
                    model.setReceta(equipoSeleccionado);
                }
            }
        });
        procesoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.guardarEstado("En proceso");
            }
        });
        listaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.guardarEstado("Lista");
            }
        });
        entregadaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.guardarEstado("Entregada");
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
            case Model.RECETAS:
                int[] cols = {TableModel.FECHARET, TableModel.MEDICAMENTOS, TableModel.ESTADO};
                recetas.setModel(new TableModel(cols, model.getRecetas()));
                break;
            case Model.CURRENT:
                idPacFld.setText(model.getCurrent().getNombre());
                int[] cols2 = {TableModel.FECHARET, TableModel.MEDICAMENTOS, TableModel.ESTADO};
                recetas.setModel(new TableModel(cols2, model.getRecetas()));
                break;
            case Model.RECETA:
                if(model.getReceta() != null) {
                    procesoButton.setEnabled(true);
                    listaButton.setEnabled(true);
                    entregadaButton.setEnabled(true);
                }
        }
        this.mainPanelDespacho.revalidate();
    }

    public JPanel getMainPanelDespacho() {
        return mainPanelDespacho;
    }
}

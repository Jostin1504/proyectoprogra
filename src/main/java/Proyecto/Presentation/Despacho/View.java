package Proyecto.Presentation.Despacho;

import Proyecto.Application;
import Proyecto.Logic.Paciente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
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
        buscarRecetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idPacFld.getText() != null) {
                    Paciente paciente = controller.getPaciente(idPacFld.getText());
                    model.setCurrent(paciente);
                }
            }
        });
        procesoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(recetas.getSelectedRow() >= 0){
                    model.getRecetas().get(recetas.getSelectedRow()).setEstado("En proceso");
                }
            }
        });
        listaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(recetas.getSelectedRow() >= 0){
                    model.getRecetas().get(recetas.getSelectedRow()).setEstado("Lista");
                }
            }
        });
        entregadaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(recetas.getSelectedRow() >= 0){
                    model.getRecetas().get(recetas.getSelectedRow()).setEstado("Entregada");
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
            case Model.RECETAS:
                int[] cols = {TableModel.FECHARET, TableModel.MEDICAMENTOS};
                recetas.setModel(new TableModel(cols, model.getRecetas()));
                break;
            case Model.CURRENT:
                idPacFld.setText(model.getCurrent().getNombre());
        }
        this.mainPanelDespacho.revalidate();
    }

    public JPanel getMainPanelDespacho() {
        return mainPanelDespacho;
    }
}

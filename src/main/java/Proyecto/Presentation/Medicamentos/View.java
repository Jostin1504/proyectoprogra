package Proyecto.Presentation.Medicamentos;

import Proyecto.Application;
import Proyecto.Logic.Medicamento;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    Controller controller;
    Model model;
    private JPanel mainPanelMedicamento;
    private JPanel JpanelMedico;
    private JPanel JpanelBusqueda;
    private JTextField idFld;
    private JTextField specialFld;
    private JTextField name1Fld;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton borrarButton;
    private JTextField name2Fld;
    private JButton buscarButton;
    private JButton reporteButton;
    private JTable medicos;
    private JPanel JpanelTable;
    private DatePicker dateFld;

    public View() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()){
                    Medicamento m = obtenerM();
                    try {
                        controller.guardarMedicamento(m);
                        JOptionPane.showMessageDialog(mainPanelMedicamento, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(mainPanelMedicamento, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()){
                    try {
                        controller.borrarMedicamento(model.getCurrent());
                        JOptionPane.showMessageDialog(mainPanelMedicamento, "MEDICO BORRADO", "", JOptionPane.INFORMATION_MESSAGE);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(mainPanelMedicamento, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.encontrarMedicamento(name2Fld.getText());
                    JOptionPane.showMessageDialog(mainPanelMedicamento, "Medico " + model.getCurrent().getNombre() + " encontrado", "", JOptionPane.INFORMATION_MESSAGE);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(mainPanelMedicamento, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        reporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
            case Model.MEDICAMENTOS:
                int[] cols = {TableModel.CODIGO, TableModel.NOMBRE, TableModel.PRESENTACION};
                medicos.setModel(new TableModel(cols,model.getMedicamentos()));
                break;
            case Model.CURRENT:
                idFld.setText(model.getCurrent().getCodigo());
                name1Fld.setText(model.getCurrent().getNombre());
                specialFld.setText(model.getCurrent().getPresentacion());
                idFld.setBackground(null);
                idFld.setToolTipText(null);
                name1Fld.setBackground(null);
                name1Fld.setToolTipText(null);
                specialFld.setBackground(null);
                specialFld.setToolTipText(null);
                break;
        }
        this.mainPanelMedicamento.revalidate();
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

        if (name1Fld.getText().isEmpty()) {
            valid = false;
            name1Fld.setBackground(Application.BACKGROUND_ERROR);
            name1Fld.setToolTipText("Nombre requerido");
        } else {
            name1Fld.setBackground(null);
            name1Fld.setToolTipText(null);
        }

        if (specialFld.getText().isEmpty()) {
            valid = false;
            specialFld.setBackground(Application.BACKGROUND_ERROR);
            specialFld.setToolTipText("Especialidad requerida");
        } else {
            specialFld.setBackground(null);
            specialFld.setToolTipText(null);
        }
        return valid;
    }
    public Medicamento obtenerM(){
        Medicamento aux = new Medicamento();
        aux.setCodigo(idFld.getText());
        aux.setNombre(name1Fld.getText());
        aux.setPresentacion(specialFld.getText());
        return aux;
    }

     public JPanel getMainPanelMedicamento() {
        return mainPanelMedicamento;
    }
    /*public void clear(){
        idFld.setText("");
        name1Fld.setText("");
        specialFld.setText("");
    }*/
}

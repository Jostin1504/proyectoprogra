package Proyecto.Presentation.Medicamentos;

import Proyecto.Application;
import Proyecto.logic.Medicamento;
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
    private JPanel JpanelMedicamentos;
    private JPanel JpanelBusqueda;
    private JTextField idFld;
    private JTextField PresentacionFld;
    private JTextField name1Fld;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton borrarButton;
    private JTextField name2Fld;
    private JButton buscarButton;
    private JButton reporteButton;
    private JTable medicamentos;
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
                        JOptionPane.showMessageDialog(mainPanelMedicamento, "MEDICAMENTO BORRADO", "", JOptionPane.INFORMATION_MESSAGE);
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
                    JOptionPane.showMessageDialog(mainPanelMedicamento, "Medicamento " + model.getCurrent().getNombre() + " encontrado", "", JOptionPane.INFORMATION_MESSAGE);
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
                medicamentos.setModel(new TableModel(cols,model.getMedicamentos()));
                break;
            case Model.CURRENT:
                idFld.setText(model.getCurrent().getCodigo());
                name1Fld.setText(model.getCurrent().getNombre());
                PresentacionFld.setText(model.getCurrent().getPresentacion());
                idFld.setBackground(null);
                idFld.setToolTipText(null);
                name1Fld.setBackground(null);
                name1Fld.setToolTipText(null);
                PresentacionFld.setBackground(null);
                PresentacionFld.setToolTipText(null);
                break;
        }
        this.mainPanelMedicamento.revalidate();
    }
    private boolean validate() {
        boolean valid = true;
        if (idFld.getText().isEmpty() || name1Fld.getText().isEmpty() || PresentacionFld.getText().isEmpty()){
            valid = false;
            JOptionPane.showMessageDialog(mainPanelMedicamento, "Asegurese de llenar los espacios vacíos");
        }
         if (idFld.getText().isEmpty()) {
            valid = false;
            idFld.setBackground(Application.BACKGROUND_ERROR);
        } else {
            idFld.setBackground(null);
            idFld.setToolTipText(null);
        }

         if (name1Fld.getText().isEmpty()) {
            valid = false;
            name1Fld.setBackground(Application.BACKGROUND_ERROR);
        } else {
            name1Fld.setBackground(null);
            name1Fld.setToolTipText(null);
        }

         if (PresentacionFld.getText().isEmpty()) {
            valid = false;
            PresentacionFld.setBackground(Application.BACKGROUND_ERROR);
        } else {
            PresentacionFld.setBackground(null);
            PresentacionFld.setToolTipText(null);
        }
        return valid;
    }
    public Medicamento obtenerM(){
        Medicamento aux = new Medicamento();
        aux.setCodigo(idFld.getText());
        aux.setNombre(name1Fld.getText());
        aux.setPresentacion(PresentacionFld.getText());
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

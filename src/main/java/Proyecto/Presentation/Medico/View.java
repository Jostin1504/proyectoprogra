package Proyecto.Presentation.Medico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import Proyecto.Application;
import Proyecto.Logic.Medico;
import com.github.lgooddatepicker.components.DatePicker;

public class View implements PropertyChangeListener {
    Controller controller;
    Model model;
    private JPanel mainPanelMedico;
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
                    Medico m = obtenerM();
                    try {
                        controller.guardarMedico(m);
                        JOptionPane.showMessageDialog(mainPanelMedico, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(mainPanelMedico, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                        controller.borrarMedico(model.getCurrent());
                        JOptionPane.showMessageDialog(mainPanelMedico, "MEDICO BORRADO", "", JOptionPane.INFORMATION_MESSAGE);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(mainPanelMedico, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.encontrarMedico(name2Fld.getText());
                    JOptionPane.showMessageDialog(mainPanelMedico, "Medico " + model.getCurrent().getNombre() + " encontrado", "", JOptionPane.INFORMATION_MESSAGE);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(mainPanelMedico, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            case Model.MEDICOS:
                int[] cols = {TableModel.ID,TableModel.NOMBRE,TableModel.ESPECIALIDAD};
                medicos.setModel(new TableModel(cols,model.getMedicos()));
                break;
            case Model.CURRENT:
                idFld.setText(model.getCurrent().getCedula());
                name1Fld.setText(model.getCurrent().getNombre());
                specialFld.setText(model.getCurrent().getEspecialidad());
                idFld.setBackground(null);
                idFld.setToolTipText(null);
                name1Fld.setBackground(null);
                name1Fld.setToolTipText(null);
                specialFld.setBackground(null);
                specialFld.setToolTipText(null);
                break;
        }
        this.mainPanelMedico.revalidate();
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
    public Medico obtenerM(){
        Medico aux = new Medico();
        aux.setCedula(idFld.getText());
        aux.setNombre(name1Fld.getText());
        aux.setEspecialidad(specialFld.getText());
        return aux;
    }

     public JPanel getMainPanelMedico() {
        return mainPanelMedico;
    }
    /*public void clear(){
        idFld.setText("");
        name1Fld.setText("");
        specialFld.setText("");
    }*/
}

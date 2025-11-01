package Proyecto.Presentation.Paciente;

import Proyecto.Application;
import Proyecto.logic.Paciente;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private Controller controller;
    private Model model;
    private JPanel mainPanelPaciente;
    private JPanel JpanelPaciente;
    private JPanel JpanelBusqueda;
    private JTextField idFld;
    private JTextField numFld;
    private JTextField name1Fld;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton borrarButton;
    private JTextField name2Fld;
    private JButton buscarButton;
    private JButton reporteButton;
    private JTable pacientes;
    private JPanel JpanelTable;
    private DatePicker dateFld;

    public View() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()){
                    Paciente p = obtenerP();
                    try {
                        controller.guardarPaciente(p);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(mainPanelPaciente, "Paciente guardado exitosamente");
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
                try {
                    controller.borrarPaciente(model.getCurrent());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(mainPanelPaciente, "Paciente borrado");
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.encontrarPaciente(name2Fld.getText());
                    JOptionPane.showMessageDialog(mainPanelPaciente, "Paciente " + model.getCurrent().getNombre() + " encontrado");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainPanelPaciente, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            case Model.PACIENTES:
                int[] cols = {TableModel.ID, TableModel.NOMBRE, TableModel.NACIMIENTO, TableModel.TELEFONO};
                pacientes.setModel(new TableModel(cols,model.getPaciente()));
                break;
            case Model.CURRENT:
                idFld.setText(model.getCurrent().getId());
                name1Fld.setText(model.getCurrent().getNombre());
                numFld.setText(model.getCurrent().getNumTelefono());
                dateFld.setText(model.getCurrent().getFechanac());
                break;
        }
    }
    private boolean validate() {
        boolean valid = true;
        if (idFld.getText().isEmpty()) {
            valid = false;
            idFld.setBorder(Application.BORDER_ERROR);  // CAMBIAR
            idFld.setToolTipText("id requerido");
        } else {
            idFld.setBorder(null);  // CAMBIAR
            idFld.setToolTipText(null);
        }

        if (name1Fld.getText().isEmpty()) {
            valid = false;
            name1Fld.setBorder(Application.BORDER_ERROR);  // CAMBIAR
            name1Fld.setToolTipText("Nombre requerido");
        } else {
            name1Fld.setBorder(null);  // CAMBIAR
            name1Fld.setToolTipText(null);
        }

        if (model.getCurrent().getNumTelefono()==null) {
            valid = false;
            numFld.setBorder(Application.BORDER_ERROR);  // CAMBIAR
            numFld.setToolTipText("Numero de telefono requerido");
        } else {
            numFld.setBorder(null);  // CAMBIAR
            numFld.setToolTipText(null);
        }

        if (model.getCurrent().getFechanac()==null) {
            valid = false;
            dateFld.setBorder(Application.BORDER_ERROR);  // CAMBIAR
            dateFld.setToolTipText("Fecha de nacimiento requerida");
        } else {
            dateFld.setBorder(null);  // CAMBIAR
            dateFld.setToolTipText(null);
        }
        return valid;
    }
    public Paciente obtenerP(){
        Paciente aux = new Paciente();
        aux.setId(idFld.getText());
        aux.setNombre(name1Fld.getText());
        aux.setNumTelefono(numFld.getText());
        aux.setFechanac(dateFld.getText());
        return aux;
    }

     public JPanel getMainPanelPaciente() {
        return mainPanelPaciente;
    }
}

package Proyecto.Presentation.Hospital.Medico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {


    private Controller controller;
    private Model model;
    private JPanel panel1;
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

    public View() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                int[] cols = {TableModel.ID,TableModel.NOMBRE};
                medicos.setModel(new TableModel(cols,model.getMedicos()));
                break;
            case Model.CURRENT:
                idFld.setText(model.getCurrent().getCedula());
                name1Fld.setText(model.getCurrent().getNombre());
                specialFld.setText(model.getCurrent().getEspecialidad());
                break;
        }
    }
}

package Proyecto.Presentation.Prescripcion.BuscarPaciente;

import Proyecto.Presentation.Prescripcion.Controller;
import Proyecto.Presentation.Prescripcion.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View extends JDialog implements PropertyChangeListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nombre;
    private JButton buscar;
    private JTable list;
    private JComboBox cedulaNombre;

    public View() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setLocationRelativeTo(null);
        setTitle("Seleccionar Paciente");
        setSize(500, 350);

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cedulaNombre.getSelectedItem().equals("Nombre")){
                    try {
                        controller.searchPacienteNombre(nombre.getText());
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(contentPane,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    try {
                        controller.searchPacienteCedula(nombre.getText());
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(contentPane,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(list.getSelectedRow() >= 0){
                    controller.setPaciente(list.getSelectedRow());
                    JOptionPane.showMessageDialog(contentPane,
                            "Paciente seleccionado: " + model.getCurrentPaciente().getNombre(),
                            "Paciente Seleccionado",
                            JOptionPane.INFORMATION_MESSAGE);
                    View.this.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(contentPane,
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.this.setVisible(false);
            }
        });
    }

    Controller controller;
    Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        if (model != null) {
            model.addPropertyChangeListener(this);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.PACIENTES:
                int[] cols = {TableModel.ID, TableModel.NOMBRE, TableModel.NACIMIENTO, TableModel.TELEFONO};
                list.setModel(new TableModel(cols, model.getPacientes()));
                list.revalidate();
                list.repaint();
                break;
        }
        this.contentPane.revalidate();
    }
}

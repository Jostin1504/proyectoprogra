package Proyecto.Presentation.Prescripcion.AgregarMedicamento;

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
    DetalleView detalleView;

    public View() throws Exception {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setLocationRelativeTo(null);
        setTitle("Medicamentos");
        setSize(400, 250);
        buttonOK.setEnabled(false);

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cedulaNombre.getSelectedItem().equals("Nombre")){
                    try {
                        controller.searchMedNombre(nombre.getText());
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(contentPane,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    try {
                        controller.searchMedCodigo(nombre.getText());
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(contentPane,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        list.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                buttonOK.setEnabled(list.getSelectedRow() >= 0);
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(list.getSelectedRow() >= 0){
                    controller.setMed(list.getSelectedRow());
                    View.this.setVisible(false); // Solo cerrar esta ventana
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

    Model model;
    Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.MEDICAMENTOS:
                int[] cols = {MedicamentoTableModel.CODIGO, MedicamentoTableModel.NOMBRE, MedicamentoTableModel.PRESENTACION};
                list.setModel(new MedicamentoTableModel(cols, model.getMedicamentos())); // Usar model en lugar de controller
                list.revalidate();
                list.repaint();
                break;
            case Model.CURRENTMED:
                if(model.getCurrentMedicamento() != null && !model.getCurrentMedicamento().getCodigo().trim().isEmpty()){
                    buttonOK.setEnabled(true);}
        }
        this.contentPane.revalidate();
    }

}

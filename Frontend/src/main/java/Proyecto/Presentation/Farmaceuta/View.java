package Proyecto.Presentation.Farmaceuta;

import Proyecto.Application;
import Proyecto.logic.Farmaceuta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private Controller controller;
    private Model model;
    private JPanel mainPanelFarmaceuta;
    private JPanel JpanelFarmaceuta;
    private JPanel JpanelBusqueda;
    private JTextField idFld;
    private JTextField name1Fld;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton borrarButton;
    private JTextField name2Fld;
    private JButton buscarButton;
    private JButton reporteButton;
    private JTable farmaceutas;
    private JPanel JpanelTable;

    public View() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()){
                    Farmaceuta f = obtenerF();
                    try {
                        controller.guardarFarmaceuta(f);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
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
                if(validate() ){
                    try {
                        controller.borrarFarmaceuta(model.getCurrent());
                        JOptionPane.showMessageDialog(mainPanelFarmaceuta, "FARMACEUTA BORRADO", "", JOptionPane.INFORMATION_MESSAGE);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(mainPanelFarmaceuta, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.encontrarFarmaceuta(name2Fld.getText());
                    JOptionPane.showMessageDialog(mainPanelFarmaceuta, "FARMACEUTA " + model.getCurrent().getNombre() + " ENCONTRADO", "", JOptionPane.INFORMATION_MESSAGE);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(mainPanelFarmaceuta, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            case Model.FARMACEUTAS:
                int[] cols = {TableModel.ID, TableModel.NOMBRE};
                farmaceutas.setModel(new TableModel(cols,model.getFarmaceutas()));
                break;
            case Model.CURRENT:
                idFld.setText(model.getCurrent().getCedula());
                name1Fld.setText(model.getCurrent().getNombre());
                break;
        }
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
        return valid;
    }
    public Farmaceuta obtenerF(){
        Farmaceuta aux = new Farmaceuta(
                name1Fld.getText(),
                idFld.getText(),
                "FAR"
        );
        return aux;
    }

     public JPanel getMainPanelFarmaceuta() {
        return mainPanelFarmaceuta;
    }
}

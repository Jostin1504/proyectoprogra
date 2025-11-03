package Proyecto.Presentation.Historico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private Controller controller;
    private Model model;
    private JPanel mainPanelHistorico;
    private JTextField name2Fld;
    private JButton buscarButton;
    private JButton limpiarButton;
    private JTable recetas;
    private JButton seleccionarPacienteButton;
    private JLabel recetasDe;
    private JLabel nombrePaciente;
    private Proyecto.Presentation.Historico.BuscarPaciente.View pacienteView;

    public View() {
        recetasDe.setVisible(false);
        seleccionarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pacienteView = new Proyecto.Presentation.Historico.BuscarPaciente.View();
                pacienteView.setController(controller);
                pacienteView.setModel(model);
                pacienteView.setVisible(true);
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
            case Model.CURRENT:
                if(!model.getCurrent().getNombre().isEmpty()){
                    recetasDe.setVisible(true);
                    nombrePaciente.setText(model.getCurrent().getNombre());
                }
                break;
            case Model.RECETAS:
                int[] cols = {TableModel.ID, TableModel.FECHARET,
                        TableModel.MEDICAMENTOS, TableModel.ESTADO};
                recetas.setModel(new TableModel(cols, model.getRecetas()));
                recetas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                break;
        }
    }
     public JPanel getMainPanelHistorico() {
        return mainPanelHistorico;
    }
}

package Proyecto.Presentation.Prescripcion.AgregarMedicamento;

import Proyecto.Presentation.Prescripcion.Controller;
import Proyecto.Presentation.Prescripcion.Model;
import Proyecto.Presentation.Prescripcion.View;
import Proyecto.Logic.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DetalleView extends JDialog implements PropertyChangeListener {
    private JTextField indicacionesFld;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JSpinner cantidadSpinner;
    private JSpinner duracionSpinner;
    private JPanel contentPane;
    Proyecto.Presentation.Prescripcion.Model model;
    Proyecto.Presentation.Prescripcion.Controller controller;
    Proyecto.Presentation.Prescripcion.View view;

    public DetalleView() throws Exception {
        model = new Model();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(guardarButton);
        setLocationRelativeTo(null);
        setTitle("Medicamentos");
        setSize(400, 250);


        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getCurrentMedicamento() != null) {
                    Medicamento medicamentoEditado = new Medicamento(
                            model.getCurrentMedicamento().getNombre(),
                            model.getCurrentMedicamento().getPresentacion(),
                            (Integer) cantidadSpinner.getValue(),
                            (Integer) duracionSpinner.getValue(),
                            indicacionesFld.getText()
                    );

                    model.addMedicamentoToReceta(medicamentoEditado);

                    model.clearCurrentMedicamento();

                    setVisible(false);
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
            case Model.CURRENTMED:

                break;
        }
        this.contentPane.revalidate();
    }

}

package Proyecto.Presentation.Prescripcion.AgregarMedicamento;

import Proyecto.Presentation.Prescripcion.Controller;
import Proyecto.Presentation.Prescripcion.Model;

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
    private boolean modoEdicion = false;
    private int indiceMedicamentoEditando = -1;

    public DetalleView() throws Exception {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(guardarButton);
        setLocationRelativeTo(null);
        setTitle("Medicamentos");
        setSize(400, 250);
        cantidadSpinner.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
        duracionSpinner.setModel(new SpinnerNumberModel(1, 1, 365, 1));

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
                    if (modoEdicion && indiceMedicamentoEditando >= 0) {
                        model.updateMedicamentoInReceta(indiceMedicamentoEditando, medicamentoEditado);
                        JOptionPane.showMessageDialog(contentPane,
                                "Medicamento actualizado correctamente",
                                "Éxito",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        model.addMedicamentoToReceta(medicamentoEditado);
                    }


                    model.clearCurrentMedicamento();
                    resetearModo();
                    setVisible(false);
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.clearCurrentMedicamento();
                setVisible(false);
            }
        });
    }

    public void setModoEdicion(boolean esEdicion, int indiceMedicamento) {
        this.modoEdicion = esEdicion;
        this.indiceMedicamentoEditando = indiceMedicamento;

        if (esEdicion) {
            setTitle("Editar Detalles del Medicamento");
            guardarButton.setText("Actualizar");

            if (model.getCurrentMedicamento() != null) {
                cantidadSpinner.setValue(model.getCurrentMedicamento().getCantidad());
                duracionSpinner.setValue(model.getCurrentMedicamento().getDuracion());
                indicacionesFld.setText(model.getCurrentMedicamento().getIndicaciones());
            }
        } else {
            setTitle("Detalles del Medicamento");
            guardarButton.setText("Guardar");
            cantidadSpinner.setValue(1);
            duracionSpinner.setValue(1);
            indicacionesFld.setText("");
        }
    }

    public void setModel(Model model) {
        this.model = model;
    }

    private void resetearModo() {
        modoEdicion = false;
        indiceMedicamentoEditando = -1;
        setTitle("Detalles del Medicamento");
        guardarButton.setText("Guardar");
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

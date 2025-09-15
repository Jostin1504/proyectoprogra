package Proyecto.Presentation.Prescripcion;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private Controller controller;
    private Model model;
    private JPanel mainPanelPrescripcion;
    private JButton agregarMedicamentoButton;
    private JButton buscarPacienteButton;
    private JTable table1;
    private JButton detallesButton;
    private JButton descartarMedicamentoButton;
    private JButton limpiarButton;
    private JButton guardarButton;
    private JLabel nomPaciente;
    private DatePicker fechaPicker;
    private Proyecto.Presentation.Prescripcion.BuscarPaciente.View pacienteView;
    private Proyecto.Presentation.Prescripcion.AgregarMedicamento.View medView;
    private Proyecto.Presentation.Prescripcion.AgregarMedicamento.DetalleView detalleView;

    public View() {
        descartarMedicamentoButton.setEnabled(false);
        detallesButton.setEnabled(false);

        buscarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pacienteView = new Proyecto.Presentation.Prescripcion.BuscarPaciente.View();
                pacienteView.setController(controller);
                pacienteView.setModel(model);
                pacienteView.setVisible(true);
            }
        });

        agregarMedicamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    medView = new Proyecto.Presentation.Prescripcion.AgregarMedicamento.View();
                    medView.setController(controller);
                    medView.setModel(model);
                    medView.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainPanelPrescripcion, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fechaRetiro = fechaPicker.getDateStringOrEmptyString();
                    controller.guardarReceta(fechaRetiro);
                    JOptionPane.showMessageDialog(mainPanelPrescripcion,
                            "Receta guardada exitosamente",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainPanelPrescripcion,
                            "Error al guardar receta: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
                JOptionPane.showMessageDialog(mainPanelPrescripcion,
                        "Receta limpiada",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public void setModel(Model model) {
        this.model = model;
        if (model != null) {
            model.addPropertyChangeListener(this);
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.CURRENTMED:
                if (model.getCurrentMedicamento() != null &&
                        !model.getCurrentMedicamento().getCodigo().trim().isEmpty()) {
                    SwingUtilities.invokeLater(() -> {
                        try {
                            if (detalleView == null) {
                                detalleView = new Proyecto.Presentation.Prescripcion.AgregarMedicamento.DetalleView();
                                detalleView.setModel(model);
                                detalleView.setController(controller);
                            }
                            detalleView.setVisible(true);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(mainPanelPrescripcion,
                                    "Error al abrir detalles: " + e.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    });
                }
                break;
            case Model.PACIENTE:
                if (model.getCurrentPaciente() != null) {
                    nomPaciente.setText(model.getCurrentPaciente().getNombre());
                    agregarMedicamentoButton.setEnabled(true);
                    guardarButton.setEnabled(true);
                } else {
                    agregarMedicamentoButton.setEnabled(false);
                    guardarButton.setEnabled(false);
                }
                break;
            case Model.CURRENT:
                if (table1 != null && model.getCurrent() != null) {
                    int[] cols = {MedTableModel.NOMBRE, MedTableModel.PRESENTACION,
                            MedTableModel.CANTIDAD, MedTableModel.INDICACIONES,
                            MedTableModel.DURACION};
                    table1.setModel(new MedTableModel(cols, model.getCurrent().getMedicamentos()));
                    table1.revalidate();
                    table1.repaint();
                }
                break;
            case Model.MEDICAMENTO:
                if (table1 != null && model.getCurrent() != null) {
                    int[] cols = {MedTableModel.NOMBRE, MedTableModel.PRESENTACION,
                            MedTableModel.CANTIDAD, MedTableModel.INDICACIONES,
                            MedTableModel.DURACION};
                    table1.setModel(new MedTableModel(cols, model.getCurrent().getMedicamentos()));
                    table1.revalidate();
                    table1.repaint();

                    boolean hayMedicamentos = !model.getCurrent().getMedicamentos().isEmpty();
                    descartarMedicamentoButton.setEnabled(hayMedicamentos);
                    detallesButton.setEnabled(hayMedicamentos);
                }
                break;
        }
    }

    public JLabel getNomPaciente(){
        return nomPaciente;
    }

    public JPanel getMainPanelPrescripcion() {
        return mainPanelPrescripcion;
    }
}
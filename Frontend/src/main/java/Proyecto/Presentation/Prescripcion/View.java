package Proyecto.Presentation.Prescripcion;

import com.github.lgooddatepicker.components.DatePicker;

import Proyecto.logic.*;
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

        descartarMedicamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(
                            mainPanelPrescripcion,
                            "¿Está seguro que desea eliminar este medicamento de la receta?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        model.getCurrent().getMedicamentos().remove(selectedRow);
                        model.firePropertyChange(Model.MEDICAMENTO); // Refrescar tabla
                        JOptionPane.showMessageDialog(mainPanelPrescripcion,
                                "Medicamento eliminado de la receta",
                                "Información",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(mainPanelPrescripcion,
                            "Seleccione un medicamento para eliminar",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
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

        detallesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow >= 0) {
                    try {
                        // Obtener el medicamento seleccionado de la receta
                        Medicamento medicamentoSelected = model.getCurrent().getMedicamentos().get(selectedRow);

                        // Configurar el medicamento actual en el modelo para edición
                        model.setCurrentMedicamento(medicamentoSelected);

                        // Crear o mostrar el DetalleView
                        if (detalleView == null) {
                            detalleView = new Proyecto.Presentation.Prescripcion.AgregarMedicamento.DetalleView();
                            detalleView.setModel(model);
                            detalleView.setController(controller);
                        }

                        detalleView.setModoEdicion(true, selectedRow);
                        detalleView.setVisible(true);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(mainPanelPrescripcion,
                                "Error al abrir detalles: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(mainPanelPrescripcion,
                            "Seleccione un medicamento para editar sus detalles",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
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
                    int[] cols = {MedTableModel.NOMBRE, MedTableModel.PRESENTACION,
                            MedTableModel.CANTIDAD, MedTableModel.INDICACIONES,
                            MedTableModel.DURACION};
                    table1.setModel(new MedTableModel(cols, model.getCurrent().getMedicamentos()));
                    table1.revalidate();
                    table1.repaint();
                break;
            case Model.MEDICAMENTO:
                if (table1 != null && model.getCurrent() != null) {
                    for (Medicamento med : model.getCurrent().getMedicamentos()) {
                        System.out.println("Med: " + med.getNombre() + " - " + med.getCantidad() + " - " + med.getDuracion());
                    }

                    int[] cols2 = {MedTableModel.NOMBRE, MedTableModel.PRESENTACION,
                            MedTableModel.CANTIDAD, MedTableModel.INDICACIONES,
                            MedTableModel.DURACION};
                    table1.setModel(new MedTableModel(cols2, model.getCurrent().getMedicamentos()));
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
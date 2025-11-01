package Proyecto.Presentation.Dashboard;

import Proyecto.logic.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import Proyecto.Presentation.ThreadListener;
import Proyecto.Presentation.SocketListener;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class Controller implements ThreadListener{
    private View view;
    private Model model;
    private ChartPanel chartPanelLinea;
    private ChartPanel chartPanelPastel;
    private SocketListener socketListener;

    public Controller(View view, Model model) throws Exception {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.addPropertyChangeListener(view);

        configurarEventos();
        inicializarDatos();

        try {
            socketListener = new SocketListener(this, Service.instance().getSid());
            socketListener.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Configura los listeners de los botones
     */
    private void configurarEventos() {
        // button1: Agregar medicamento seleccionado de comboBox5
        view.getButton1().addActionListener(e -> agregarMedicamentoSeleccionado());

        // button2: Agregar todos los medicamentos
        view.getButton2().addActionListener(e -> agregarTodosMedicamentos());

        // button3: Remover medicamento seleccionado de la tabla
        view.getButton3().addActionListener(e -> removerMedicamentoSeleccionado());

        // button4: Actualizar gráficos
        view.getButton4().addActionListener(e -> actualizarGraficos());
    }

    /**
     * Carga datos iniciales
     */
    public void inicializarDatos() throws Exception {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Cargar medicamentos
                List<Medicamento> medicamentos = Service.instance().getMedicamentos();
                model.setMedicamentos(medicamentos);

                // Cargar recetas
                List<Receta> recetas = Service.instance().buscarTodasRecetas();
                model.setTodasLasRecetas(recetas);

                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    inicializarComboBoxMedicamentos();
                    actualizarGraficos(); // Generar gráficos iniciales
                    JOptionPane.showMessageDialog(
                            view.getMainPanelDashboard(),
                            "Datos cargados: " + model.getMedicamentos().size() +
                                    " medicamentos y " + model.getTodasLasRecetas().size() + " recetas",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(
                            view.getMainPanelDashboard(),
                            "Error al cargar datos: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }


    @Override
    public void deliver_message(String message) {
        SwingUtilities.invokeLater(() -> {
            try {
                inicializarDatos(); // Recargar datos cuando haya cambios
                System.out.println("Dashboard actualizado: " + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void stop() {
        if (socketListener != null) {
            socketListener.stop();
        }
    }
    /**
     * Inicializa el comboBox5 con los medicamentos disponibles
     */
    private void inicializarComboBoxMedicamentos() {
        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();
        for (Medicamento med : model.getMedicamentos()) {
            comboModel.addElement(med.getCodigo() + " - " + med.getNombre());
        }
        view.getComboBox5().setModel(comboModel);
    }

    /**
     * Agrega el medicamento seleccionado en comboBox5 a la lista
     */
    private void agregarMedicamentoSeleccionado() {
        String seleccion = (String) view.getComboBox5().getSelectedItem();
        if (seleccion == null) return;

        String codigo = seleccion.split(" - ")[0];
        Medicamento med = model.getMedicamentos().stream()
                .filter(m -> m.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);

        if (med != null) {
            model.agregarMedicamentoSeleccionado(med);
        }
    }

    /**
     * Agrega todos los medicamentos a la lista
     */
    private void agregarTodosMedicamentos() {
        for (Medicamento med : model.getMedicamentos()) {
            model.agregarMedicamentoSeleccionado(med);
        }
    }

    /**
     * Remueve el medicamento seleccionado de la tabla
     */
    private void removerMedicamentoSeleccionado() {
        int selectedRow = view.getMedicamentos().getSelectedRow();
        if (selectedRow >= 0) {
            Medicamento med = model.getMedicamentosSeleccionados().get(selectedRow);
            model.removerMedicamentoSeleccionado(med);
        } else {
            JOptionPane.showMessageDialog(
                    view.getMainPanelDashboard(),
                    "Seleccione un medicamento de la tabla",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    /**
     * Actualiza ambos gráficos
     */
    public void actualizarGraficos() {
        if (model.getMedicamentosSeleccionados().isEmpty()) {
            JOptionPane.showMessageDialog(
                    view.getMainPanelDashboard(),
                    "Agregue al menos un medicamento antes de actualizar los gráficos",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                return null;
            }

            @Override
            protected void done() {
                try {
                    actualizarGraficoLinea();
                    actualizarGraficoPastel();
                    model.setChart1(new Object());
                    model.setChart2(new Object());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(
                            view.getMainPanelDashboard(),
                            "Error al actualizar gráficos: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    /**
     * Genera y muestra el gráfico de línea (medicamentos por mes)
     */
    private void actualizarGraficoLinea() {
        try {
            // Obtener fechas seleccionadas
            String añoInicio = (String) view.getComboBox1().getSelectedItem();
            String mesInicio = (String) view.getComboBox2().getSelectedItem();
            String añoFin = (String) view.getComboBox3().getSelectedItem();
            String mesFin = (String) view.getComboBox4().getSelectedItem();

            // Generar datos
            Map<String, Map<String, Integer>> datos =
                    model.generarDatosMedicamentosPorMes(añoInicio, mesInicio, añoFin, mesFin);

            // Crear dataset
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Map.Entry<String, Map<String, Integer>> entry : datos.entrySet()) {
                String medicamento = entry.getKey();
                for (Map.Entry<String, Integer> mesEntry : entry.getValue().entrySet()) {
                    dataset.addValue(mesEntry.getValue(), medicamento, mesEntry.getKey());
                }
            }

            // Crear gráfico
            JFreeChart chart = ChartFactory.createLineChart(
                    "Medicamentos Prescritos por Mes",
                    "Mes",
                    "Cantidad",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            // Personalizar
            chart.getPlot().setBackgroundPaint(Color.WHITE);
            chart.setBackgroundPaint(new Color(240, 240, 240));

            // Actualizar panel
            JPanel panelMedicamentos = view.getPanelMedicamentos();
            panelMedicamentos.removeAll();

            chartPanelLinea = new ChartPanel(chart);
            chartPanelLinea.setPreferredSize(new Dimension(500, 400));

            panelMedicamentos.setLayout(new BorderLayout());
            panelMedicamentos.add(chartPanelLinea, BorderLayout.CENTER);
            panelMedicamentos.revalidate();
            panelMedicamentos.repaint();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error generando gráfico de línea: " + e.getMessage());
        }
    }

    /**
     * Genera y muestra el gráfico de pastel (recetas por estado)
     */
    private void actualizarGraficoPastel() {
        try {
            // Generar datos
            Map<String, Integer> datos = model.generarDatosRecetasPorEstado();

            if (datos.isEmpty()) {
                JOptionPane.showMessageDialog(
                        view.getMainPanelDashboard(),
                        "No hay recetas para mostrar",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }

            // Crear dataset
            DefaultPieDataset dataset = new DefaultPieDataset();
            datos.forEach(dataset::setValue);

            // Crear gráfico
            JFreeChart chart = ChartFactory.createPieChart(
                    "Recetas por Estado",
                    dataset,
                    true,
                    true,
                    false
            );

            // Personalizar
            chart.setBackgroundPaint(new Color(240, 240, 240));

            // Actualizar panel
            JPanel panelRecetas = view.getPanelRecetas();
            panelRecetas.removeAll();

            chartPanelPastel = new ChartPanel(chart);
            chartPanelPastel.setPreferredSize(new Dimension(500, 500));

            panelRecetas.setLayout(new BorderLayout());
            panelRecetas.add(chartPanelPastel, BorderLayout.CENTER);
            panelRecetas.revalidate();
            panelRecetas.repaint();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error generando gráfico de pastel: " + e.getMessage());
        }
    }
}

package Proyecto.Presentation.Dashboard;

import Proyecto.Logic.Service;
import Proyecto.Logic.Medicamento;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.util.List;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.addPropertyChangeListener(view);
        inicializarDatos();
    }

    public void inicializarDatos() {
        List<Medicamento> medicamentos = Service.instance().getMedicamentos();
        model.setMedicamentos(medicamentos);
        actualizarGraficos();
    }

    public void actualizarGraficos() {
        model.setChart1(new Object());
        model.setChart2(new Object());
    }

    public CategoryDataset createDataset(List<Medicamento> medicamentos) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (medicamentos == null || medicamentos.isEmpty()) {
            return dataset;
        }

        for (Medicamento medicamento : medicamentos) {
            try {
                String categoria = medicamento.getNombre();
                String serie = medicamento.getPresentacion();
                Number cantidad = Service.instance().getCantidadTotalMedicamento(medicamento);

                dataset.addValue(cantidad, categoria, serie);
            } catch (Exception e) {
                System.err.println("Error al agregar medicamento al dataset: " + e.getMessage());
            }
        }

        return dataset;
    }

    public DefaultPieDataset createPieDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        int enProceso = Service.instance().recetasEnProceso();
        int listas = Service.instance().recetasListas();
        int entregadas = Service.instance().recetasEntregadas();
        int confeccionadas = Service.instance().recetasConfeccionadas();

        if (enProceso > 0) dataset.setValue("En proceso", enProceso);
        if (listas > 0) dataset.setValue("Lista", listas);
        if (entregadas > 0) dataset.setValue("Entregada", entregadas);
        if (confeccionadas > 0) dataset.setValue("Confeccionada", confeccionadas);

        return dataset;
    }

    public void agregarMedicamento(Medicamento medicamento) {
        if (!model.getMedicamentos().contains(medicamento)) {
            model.getMedicamentos().add(medicamento);
            model.setMedicamentos(model.getMedicamentos()); // Actualizar vista
            actualizarGraficos();
        }
    }

    public void removerMedicamento(Medicamento medicamento) {
        model.getMedicamentos().remove(medicamento);
        model.setMedicamentos(model.getMedicamentos()); // Actualizar vista
        actualizarGraficos();
    }

    public void limpiarMedicamentos() {
        model.getMedicamentos().clear();
        model.setMedicamentos(model.getMedicamentos());
        actualizarGraficos();
    }
}

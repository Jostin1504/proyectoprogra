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
    }

    public CategoryDataset createDataset(List<Medicamento> medicamentos) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for  (Medicamento medicamento : medicamentos) {
            try {
                dataset.addValue(Service.instance().getCantidadTotalMedicamento(medicamento),medicamento.getNombre(), medicamento.getDuracion());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        //dataset.addValue(); //eje Y, categoria en la que entra, eje X
        return dataset;
    }

   public DefaultPieDataset createPieDataset() {
       DefaultPieDataset dataset = new DefaultPieDataset();
       dataset.setValue("En proceso", Service.instance().recetasEnProceso());
       dataset.setValue("Lista", Service.instance().recetasListas());
       dataset.setValue("Entregada", Service.instance().recetasEntregadas());
       dataset.setValue("Confeccionada", Service.instance().recetasConfeccionadas());
       return dataset;
   }
}

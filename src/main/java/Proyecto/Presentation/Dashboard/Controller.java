package Proyecto.Presentation.Dashboard;

import Proyecto.Logic.Service;
import Proyecto.Logic.Medicamento;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

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

    /*public CategoryDataset createDataset(List<Medicamento> medicamentos) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for  (Medicamento medicamento : medicamentos) {
            dataset.addValue(Service.instance().getCantidadTotalMedicamento(medicamento),medicamento.getNombre(), medicamento.getDuracion());
        }
        //dataset.addValue(); //eje Y, categoria en la que entra, eje X
        return dataset;
    }*/

   /* public void grafico1(){
        model.setChart1(ChartFactory.createLineChart("Medicamentos","Mes", "Cantidad",createDataset(model.medicamentos)));
    }*/
}

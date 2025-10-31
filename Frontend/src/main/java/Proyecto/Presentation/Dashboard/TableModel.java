package Proyecto.Presentation.Dashboard;

import Proyecto.logic.Medicamento;
import Proyecto.logic.Service;
import Proyecto.Presentation.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Medicamento> implements javax.swing.table.TableModel {
    public TableModel(int[] cols, List<Medicamento> rows) {
        super(cols, rows);
    }

    public static final int NOMBRE = 0;
    public static final int PRESENTACION = 1;
    public static final int CANTIDAD = 2;

    @Override
    protected void initColNames() {
        colNames = new String[3];
        colNames[NOMBRE] = "Nombre";
        colNames[PRESENTACION] = "Presentación";
        colNames[CANTIDAD] = "Cantidad Total";
    }

    @Override
    protected Object getPropetyAt(Medicamento e, int col) {
        switch (cols[col]) {
            case NOMBRE:
                return e.getNombre();
            case PRESENTACION:
                return e.getPresentacion();

            default:
                return "";
        }
    }
}

package Proyecto.Presentation.Dashboard;

import Proyecto.Logic.Medicamento;
import Proyecto.Logic.Service;
import Proyecto.Presentation.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Medicamento> implements javax.swing.table.TableModel {
    public TableModel(int[] cols, List<Medicamento> rows) {
        super(cols, rows);
    }

    public static final int NOMBRE = 0;

    @Override
    protected void initColNames() {
        colNames = new String[5];
        colNames[NOMBRE] = "Nombre";
    }
    @Override
    protected Object getPropetyAt(Medicamento e, int col) {
        switch (cols[col]) {
            case NOMBRE:
                return e.getNombre();
            default:
                return "";
        }
    }
}

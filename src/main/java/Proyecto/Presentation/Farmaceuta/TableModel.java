package Proyecto.Presentation.Farmaceuta;

import Proyecto.Logic.Farmaceuta;
import Proyecto.Presentation.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Farmaceuta> implements javax.swing.table.TableModel {
    public TableModel(int[] cols, List<Farmaceuta> rows) {
        super(cols, rows);
    }

    public static final int ID = 0;
    public static final int NOMBRE = 1;

    @Override
    protected void initColNames() {
        colNames = new String[5];
        colNames[ID] = "Cédula";
        colNames[NOMBRE] = "Nombre";
    }
    @Override
    protected Object getPropetyAt(Farmaceuta e, int col) {
        switch (cols[col]) {
            case ID:
                return e.getCedula();
            case NOMBRE:
                return e.getNombre();
            default:
                return "";
        }
    }
}

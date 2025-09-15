package Proyecto.Presentation.Despacho;

import Proyecto.Logic.Recetas;
import Proyecto.Presentation.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Recetas> implements javax.swing.table.TableModel {
    public TableModel(int[] cols, List<Recetas> rows) {
        super(cols, rows);
    }


    public static final int FECHARET = 0;
    public static final int MEDICAMENTOS = 1;

    @Override
    protected void initColNames() {
        colNames = new String[2];
        colNames[FECHARET] = "Fecha de retiro";
        colNames[MEDICAMENTOS] = "Medicamento";
    }
    @Override
    protected Object getPropetyAt(Recetas e, int col) {
        switch (cols[col]) {
            case FECHARET:
                return e.getFechaRetiro();
            case MEDICAMENTOS:
                return e.getMedicamentos();
            default:
                return "";
        }
    }
}

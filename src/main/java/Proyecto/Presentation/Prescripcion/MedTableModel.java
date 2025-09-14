package Proyecto.Presentation.Prescripcion;

import Proyecto.Logic.*;
import Proyecto.Presentation.AbstractTableModel;

import java.util.List;

public class MedTableModel extends AbstractTableModel<Medicamento> implements javax.swing.table.TableModel {
    public MedTableModel(int[] cols, List<Medicamento> rows) {
        super(cols, rows);
    }


    public static final int NOMBRE = 0;
    public static final int PRESENTACION = 1;
    public static final int CANTIDAD = 2;
    public static final int INDICACIONES = 3;
    public static final int DURACION = 4;

    @Override
    protected void initColNames() {
        colNames = new String[5];
        colNames[NOMBRE] = "Medicamento";
        colNames[PRESENTACION] = "Presentación";
        colNames[CANTIDAD] = "Cantidad";
        colNames[INDICACIONES] = "Indicaciones";
        colNames[DURACION] = "Duración";
    }
    @Override
    protected Object getPropetyAt(Medicamento e, int col) {
        switch (cols[col]) {
            case NOMBRE:
                return e.getCodigo();
            case PRESENTACION:
                return e.getNombre();
            case CANTIDAD:
                return e.getPresentacion();
            case  INDICACIONES:
                 return e.getIndicaciones();
            case DURACION:
                 return e.getDuracion();
            default:
                return "";
        }
    }
}

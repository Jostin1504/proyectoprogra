package Proyecto.Presentation.Prescripcion;

import Proyecto.Presentation.AbstractTableModel;
import Proyecto.Logic.Medicamento;

import java.util.List;

public class TableModel extends AbstractTableModel<Medicamento> implements javax.swing.table.TableModel {
    public TableModel(int[] cols, List<Medicamento> rows) {
        super(cols, rows);
    }

    public static final int MEDICAMENTO = 0;
    public static final int PRESENTACION = 1;
    public static final int CANTIDAD = 2;
    public static final int INDICACIONES = 3;
    public static final int DURACION = 4;

    @Override
    protected void initColNames() {
        colNames = new String[5];
        colNames[MEDICAMENTO] = "Medicamento";
        colNames[PRESENTACION] = "Presentacion";
        colNames[CANTIDAD] = "Cantidad";
        colNames[INDICACIONES] = "Indicaciones";
        colNames[DURACION] = "Duracion";

    }
    @Override
    protected Object getPropetyAt(Medicamento e, int col) {
        switch (cols[col]) {
            case MEDICAMENTO:
                return e.getNombre();
            case PRESENTACION:
                return e.getPresentacion();
                case CANTIDAD:
                    return e.getCantidad();
                    case INDICACIONES:
                        return e.getIndicaciones();
                        case DURACION:
                            return e.getDuracion();
            default:
                return "";
        }
    }
}

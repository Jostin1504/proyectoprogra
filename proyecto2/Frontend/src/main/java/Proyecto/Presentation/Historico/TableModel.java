package Proyecto.Presentation.Historico;

import Proyecto.Presentation.AbstractTableModel;
import Proyecto.logic.Medicamento;
import Proyecto.logic.Receta;

import java.util.List;

public class TableModel extends AbstractTableModel<Receta> implements javax.swing.table.TableModel {
    public TableModel(int[] cols, List<Receta> rows) {
        super(cols, rows);
    }

    public static final int FECHARET = 1;
    public static final int MEDICAMENTOS = 2;
    public static final int ESTADO = 3;
    public static final int ID = 0;

    @Override
    protected void initColNames() {
        colNames = new String[4];
        colNames[ID] = "Paciente(ID)";
        colNames[FECHARET] = "Fecha de retiro";
        colNames[MEDICAMENTOS] = "Medicamento";
        colNames[ESTADO] = "Estado";
    }
    @Override
    protected Object getPropetyAt(Receta e, int col) {
        switch (cols[col]) {
            case FECHARET:
                return e.getFechaRetiro();
            case MEDICAMENTOS:
                if (e.getMedicamentos() == null || e.getMedicamentos().isEmpty()) {
                    return "Sin medicamentos";
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < e.getMedicamentos().size(); i++) {
                    Medicamento med = e.getMedicamentos().get(i);
                    sb.append(med.getNombre())
                            .append(" - ")
                            .append(med.getPresentacion());
                    if (i < e.getMedicamentos().size() - 1) {
                        sb.append("; ");
                    }
                }
                return sb.toString();
                case ESTADO:
                    return e.getEstado();
                case ID:
                    return e.getIdPaciente();
            default:
                return "";
        }
    }
}

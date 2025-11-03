package Proyecto.Presentation.Historico.BuscarPaciente;

import Proyecto.Presentation.AbstractTableModel;
import Proyecto.logic.Paciente;

import java.util.List;

public class TableModel extends AbstractTableModel<Paciente> implements javax.swing.table.TableModel {
    public TableModel(int[] cols, List<Paciente> rows) {
        super(cols, rows);
    }

    public static final int ID = 0;
    public static final int NOMBRE = 1;
    public static final int NACIMIENTO = 2;
    public static final int TELEFONO = 3;

    @Override
    protected void initColNames() {
        colNames = new String[5];
        colNames[ID] = "Cédula";
        colNames[NOMBRE] = "Nombre";
        colNames[NACIMIENTO] = "Nacimiento";
        colNames[TELEFONO] = "Telefono";
    }
    @Override
    protected Object getPropetyAt(Paciente e, int col) {
        switch (cols[col]) {
            case ID:
                return e.getId();
            case NOMBRE:
                return e.getNombre();
            case NACIMIENTO:
                return e.getFechanac();
            case TELEFONO:
                return e.getNumTelefono();
            default:
                return "";
        }
    }
}

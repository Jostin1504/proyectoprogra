package Proyecto.Presentation.UsuariosActivos;

import Proyecto.Presentation.AbstractTableModel;
import Proyecto.logic.*;
import java.util.List;

public class TableModel extends AbstractTableModel<Usuario> implements javax.swing.table.TableModel {

    public TableModel(int[] cols, List<Usuario> rows) {
        super(cols, rows);
    }

    public static final int ID = 0;  // Cédula del usuario

    @Override
    protected void initColNames() {
        colNames = new String[1];
        colNames[ID] = "Id";
    }

    @Override
    protected Object getPropetyAt(Usuario e, int col) {
        switch (cols[col]) {
            case ID:
                return e.getCedula();
            default:
                return "";
        }
    }
}

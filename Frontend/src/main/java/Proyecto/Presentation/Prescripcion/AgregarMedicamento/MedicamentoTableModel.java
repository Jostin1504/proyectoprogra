package Proyecto.Presentation.Prescripcion.AgregarMedicamento;

import Proyecto.Logic.Medicamento;
import Proyecto.Presentation.AbstractTableModel;

import java.util.List;

public class MedicamentoTableModel extends AbstractTableModel<Medicamento> implements javax.swing.table.TableModel {
    public MedicamentoTableModel(int[] cols, List<Medicamento> rows) {
        super(cols, rows);
    }


    public static final int CODIGO = 0;
    public static final int NOMBRE = 1;
    public static final int PRESENTACION = 2;

    @Override
    protected void initColNames() {
        colNames = new String[3];
        colNames[CODIGO] = "Código";
        colNames[NOMBRE] = "Nombre";
        colNames[PRESENTACION] = "Presentación";
    }
    @Override
    protected Object getPropetyAt(Medicamento e, int col) {
        switch (cols[col]) {
            case CODIGO:
                return e.getCodigo();
            case NOMBRE:
                return e.getNombre();
            case PRESENTACION:
                return e.getPresentacion();
            default:
                return "";
        }
    }
}

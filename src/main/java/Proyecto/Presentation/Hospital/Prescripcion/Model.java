package Proyecto.Presentation.Hospital.Prescripcion;

import Proyecto.Logic.Medicamento;
import Proyecto.Presentation.Hospital.AbstractModel;

public class Model extends AbstractModel {
    Medicamento current;

    public void setCurrent(Medicamento current) {
        this.current = current;
    }
}

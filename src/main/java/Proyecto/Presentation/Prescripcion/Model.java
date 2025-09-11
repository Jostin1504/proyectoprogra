package Proyecto.Presentation.Prescripcion;

import Proyecto.Logic.Medicamento;
import Proyecto.Presentation.AbstractModel;

public class Model extends AbstractModel {
    Medicamento current;

    public void setCurrent(Medicamento current) {
        this.current = current;
    }
}

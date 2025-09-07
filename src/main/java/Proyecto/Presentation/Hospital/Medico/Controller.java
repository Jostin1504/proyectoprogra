package Proyecto.Presentation.Hospital.Medico;

import Proyecto.Logic.Medico;
import Proyecto.Presentation.Hospital.Medico.Model;
import Proyecto.Presentation.Hospital.Medico.View;
import Proyecto.Logic.Service;

public class Controller {
    private Proyecto.Presentation.Hospital.Medico.View view;
    private Proyecto.Presentation.Hospital.Medico.Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    void clear(){
        model.setCurrent(new Medico());
    }

    //obtener lista de doctores
    //guardar doctores en data
    //encontrar doctores en data
    //borrar doctores de la lista

}

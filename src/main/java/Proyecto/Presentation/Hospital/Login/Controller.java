package Proyecto.Presentation.Hospital.Login;

import Proyecto.Logic.Usuario;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void clear() {
        model.setCurrent(new Usuario());
    }

}

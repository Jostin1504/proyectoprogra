package Proyecto.Presentation.Hospital.Prescripcion;

import Proyecto.Presentation.Hospital.Prescripcion.Controller;
import Proyecto.Presentation.Hospital.Prescripcion.Model;
import Proyecto.Presentation.Hospital.Prescripcion.TableModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private Controller controller;
    private Model model;



    public void setModel(Model model) {
        this.model = model;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {

        }
    }
}

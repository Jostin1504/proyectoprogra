package Proyecto.Presentation.Historico;

import Proyecto.logic.*;

import java.util.List;

public class Controller {
      View view;
      Model model;

    public Controller(View view, Model model) throws Exception {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.addPropertyChangeListener(view);
        model.setRecetas(Service.instance().buscarTodasRecetas());
    }

    void clear(){
        model.setCurrent(new Paciente());
    }

    //obtener lista de medicos
    public List<Receta> getRecetas() throws Exception {
        return Service.instance().buscarTodasRecetas();
    }


    //encontrar medicos en data con nombre
    public void encontrarRecetasPaciente(String id)throws Exception{
        List<Receta> f = Service.instance().buscarRecetasPorPaciente(id);
        if (f == null) {
            throw new Exception("Paciente con id '" + id + "' no encontrado");
        }
        model.setRecetas(f);
    }

}

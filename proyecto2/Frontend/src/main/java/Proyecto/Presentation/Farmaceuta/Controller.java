package Proyecto.Presentation.Farmaceuta;

import Proyecto.logic.Farmaceuta;
import Proyecto.logic.Service;

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
        model.setFarmaceutas(Service.instance().getFarmaceutas());
    }

    void clear(){
        model.setCurrent(new Farmaceuta());
    }

    //obtener lista de medicos
    public List<Farmaceuta> getFarmaceutas() throws Exception {
        return Service.instance().getFarmaceutas();
    }

    //guardar medicos en data
    public void guardarFarmaceuta(Farmaceuta farmaceuta) throws Exception {
        Service.instance().anadirFarmaceuta(farmaceuta);
        model.setCurrent(new Farmaceuta());
        model.setFarmaceutas(Service.instance().getFarmaceutas());
    }

    //encontrar medicos en data con nombre
    public void encontrarFarmaceuta(String nom)throws Exception{
        Farmaceuta f = Service.instance().buscarFarmaceuta(nom);
        if (f == null) {
            throw new Exception("Farmaceuta con nombre '" + nom + "' no encontrado");
        }
        model.setCurrent(f);
    }

    //borrar medicos de la lista
    public void borrarFarmaceuta(Farmaceuta farmaceuta) throws Exception {
        Service.instance().eliminarFarmaceuta(farmaceuta);
        model.setCurrent(new Farmaceuta());
    }
}

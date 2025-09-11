package Proyecto.Presentation.Farmaceuta;

import Proyecto.Logic.Farmaceuta;
import Proyecto.Logic.Service;

import java.util.List;

public class Controller {
      View view;
      Model model;

    public Controller(View view, Model model) {
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
    public List<Farmaceuta> getFarmaceutas(){
        return Service.instance().getFarmaceutas();
    }

    //guardar medicos en data
    public void guardarFarmaceuta(Farmaceuta farmaceuta){
        Service.instance().anadirFarmaceuta(farmaceuta);
        model.setCurrent(new Farmaceuta());
        model.setFarmaceutas(Service.instance().getFarmaceutas());
    }

    //encontrar medicos en data con nombre
    public void encontrarFarmaceuta(String nom)throws Exception{
         model.setCurrent(Service.instance().buscarFarmaceuta(nom));
    }

    //borrar medicos de la lista
    public void borrarFarmaceuta(Farmaceuta farmaceuta){
        Service.instance().eliminarFarmaceuta(farmaceuta);
        model.setCurrent(new Farmaceuta());
    }
}

package Proyecto.Presentation.Hospital.Farmaceuta;

import Proyecto.Logic.Farmaceuta;
import Proyecto.Logic.Service;

import java.util.List;

public class Controller {
    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
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
    }

    //encontrar medicos en data con nombre
    public Farmaceuta encontrarFarmaceuta(String nom){
        return Service.instance().buscarFarmaceuta(nom);
    }

    //borrar medicos de la lista
    public void borrarFarmaceuta(Farmaceuta farmaceuta){
        Service.instance().eliminarFarmaceuta(farmaceuta);
        model.setCurrent(new Farmaceuta());
    }
}

package Proyecto.Logic;

import Proyecto.Logic.Usuario;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "farmaceuta")
@XmlAccessorType(XmlAccessType.FIELD)
public class Farmaceuta extends Usuario {
   public Farmaceuta(String nombre, String cedula, String rol) {
        super(nombre, cedula, rol);
    }

    public Farmaceuta() {
       super();
    }
}

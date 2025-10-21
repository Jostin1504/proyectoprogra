package Proyecto.Logic;

import Proyecto.Logic.Usuario;
import jakarta.xml.bind.annotation.*;

public class Farmaceuta extends Usuario {
   public Farmaceuta(String nombre, String cedula, String rol) {
        super(nombre, cedula, rol);
    }

    public Farmaceuta() {
       super();
    }
}

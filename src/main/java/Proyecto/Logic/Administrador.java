package Proyecto.Logic;

import Proyecto.Logic.Usuario;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "administrador")
@XmlAccessorType(XmlAccessType.FIELD)
public class Administrador extends Usuario{

    public Administrador(String nombre, String cedula, String rol) {super(nombre, cedula, rol);}

    public Administrador() {
        super();
    }
}

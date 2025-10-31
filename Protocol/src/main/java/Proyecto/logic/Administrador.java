package Proyecto.logic;

import java.io.Serializable;

public class Administrador extends Usuario implements Serializable{

    public Administrador(String nombre, String cedula, String rol) {super(nombre, cedula, rol);}

    public Administrador() {
        super();
    }
}

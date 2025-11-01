package Proyecto.logic;

import java.io.Serializable;

public class Farmaceuta extends Usuario implements Serializable {
    public Farmaceuta(String nombre, String cedula, String rol) {
        super(nombre, cedula, "FAR");
    }

    public Farmaceuta() {
        super();
        this.rol = "FAR";
    }
    }
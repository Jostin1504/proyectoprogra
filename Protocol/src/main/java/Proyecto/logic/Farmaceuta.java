package Proyecto.logic;

public class Farmaceuta extends Usuario {
    public Farmaceuta(String nombre, String cedula, String rol) {
        super(nombre, cedula, "FAR");
    }

    public Farmaceuta() {
        super();
        this.rol = "FAR";
    }
    }
package Proyecto.logic;

import java.io.Serializable;

public class Medico extends Usuario implements Serializable {
    private String especialidad;


    public Medico(String nombre, String cedula, String rol, String especialidad) {
        super(nombre, cedula, "MED");
        this.especialidad = especialidad;
    }

    public Medico() {
        super();
        this.rol = "MED";
        especialidad = "";
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}

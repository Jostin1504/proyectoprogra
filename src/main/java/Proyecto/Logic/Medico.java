package Proyecto.Logic;

import Proyecto.Logic.Usuario;

public class Medico extends Usuario {
    String especialidad;


    public Medico(String nombre, String cedula, String especialidad) {
        super(nombre, cedula);
    }

    public Medico() {
        super();
        especialidad = "";
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}

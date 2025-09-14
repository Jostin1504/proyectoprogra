package Proyecto.Logic;

import Proyecto.Logic.Usuario;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "medico")
@XmlAccessorType(XmlAccessType.FIELD)
public class Medico extends Usuario {
    String especialidad;


    public Medico(String nombre, String cedula, String rol, String especialidad) {
        super(nombre, cedula, rol);
        this.especialidad = especialidad;
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
